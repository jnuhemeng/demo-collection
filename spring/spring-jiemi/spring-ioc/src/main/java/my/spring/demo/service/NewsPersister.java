package my.spring.demo.service;

import my.spring.demo.domain.NewsBean;

public interface NewsPersister {
	
	void persistNews(NewsBean newsBean);
}