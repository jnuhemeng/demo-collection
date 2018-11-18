package my.spring.demo.service.impl;

import java.util.Map;
import java.util.HashMap;

import my.spring.demo.domain.NewsBean;
import my.spring.demo.service.NewsListener;
import org.springframework.stereotype.Component;

@Component
public class FXNewsListener implements NewsListener {

	private Map<String, NewsBean> fxNewsMap;

	public FXNewsListener() {
		fxNewsMap = new HashMap<String, NewsBean>();
		fxNewsMap.put("FXN-AA-01", new NewsBean("FXN-AA-01", "The Title of First News"));
		fxNewsMap.put("FXN-AA-02", new NewsBean("FXN-AA-02", "This Is The Second News"));
		fxNewsMap.put("FXN-AA-03", new NewsBean("FXN-AA-03", "Another News"));
	}

	public String[] getAvailableNewsIds() {
		return fxNewsMap.keySet().toArray(new String[1]);
	}

	public NewsBean getNewsByPK(String newsId) {
		return fxNewsMap.get(newsId);
	}

	public void postProcessIfNecessary(String newsId) {
		System.out.println("posting: " + newsId);
	}

}
