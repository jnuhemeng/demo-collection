package my.spring.demo.service;

import my.spring.demo.domain.NewsBean;

public interface NewsListener {

	String[] getAvailableNewsIds();

	NewsBean getNewsByPK(String newsId);
	
	void postProcessIfNecessary(String newsId);
}
