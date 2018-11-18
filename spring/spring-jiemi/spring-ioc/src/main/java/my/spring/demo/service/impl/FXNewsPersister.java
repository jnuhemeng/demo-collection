package my.spring.demo.service.impl;

import my.spring.demo.domain.NewsBean;
import my.spring.demo.service.NewsPersister;
import org.springframework.stereotype.Component;

@Component
public class FXNewsPersister implements NewsPersister {

	public void persistNews(NewsBean newsBean) {
		System.out.println("persisting: " + newsBean);
	}
}