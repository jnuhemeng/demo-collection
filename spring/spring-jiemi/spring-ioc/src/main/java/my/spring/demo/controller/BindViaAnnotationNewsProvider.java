package my.spring.demo.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BindViaAnnotationNewsProvider {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-annotation.xml");
        NewsProvider newsProvider = (NewsProvider)applicationContext.getBean("newsProvider");

        newsProvider.getAndPersistNews();
    }

}
