package my.spring.demo.controller;

import my.spring.demo.domain.NewsBean;
import my.spring.demo.service.NewsListener;
import my.spring.demo.service.NewsPersister;
import my.spring.demo.service.impl.FXNewsListener;
import my.spring.demo.service.impl.FXNewsPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsProvider {

	@Autowired
	private NewsListener newsListener;

	@Autowired
	private NewsPersister newsPersister;

	public NewsProvider() {

	}

	public NewsProvider(NewsListener newsListener, NewsPersister newsPersister) {
		this.newsListener = newsListener;
		this.newsPersister = newsPersister;
	}

	void getAndPersistNews() {
		String[] newsIds = newsListener.getAvailableNewsIds();
		if(newsIds == null || newsIds.length == 0) {
			return;
		}

		for(String newId : newsIds) {
			NewsBean newsBean = newsListener.getNewsByPK(newId);
			newsPersister.persistNews(newsBean);
			newsListener.postProcessIfNecessary(newId);
		}
	}


	public static void main(String[] args) {
		NewsProvider newsProvider = new NewsProvider(new FXNewsListener(), new FXNewsPersister());
		newsProvider.getAndPersistNews();
	}

}