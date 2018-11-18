package my.spring.demo.controller;

import my.spring.demo.service.impl.FXNewsListener;
import my.spring.demo.service.impl.FXNewsPersister;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class BindViaCodeNewsProvider {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
		BeanFactory container = bindViaCode(beanRegistry);
		NewsProvider newsProvider = (NewsProvider)container.getBean("newsProvider");
		newsProvider.getAndPersistNews();
	}

	private static BeanFactory bindViaCode(DefaultListableBeanFactory beanRegistry) {
		AbstractBeanDefinition newsProvider = new RootBeanDefinition(NewsProvider.class);
		AbstractBeanDefinition newsListener = new RootBeanDefinition(FXNewsListener.class);
		AbstractBeanDefinition newsPersister = new RootBeanDefinition(FXNewsPersister.class);

		beanRegistry.registerBeanDefinition("newsProvider", newsProvider);
		beanRegistry.registerBeanDefinition("newsListener", newsListener);
		beanRegistry.registerBeanDefinition("newsPersister", newsPersister);

		ConstructorArgumentValues argumentValues = new ConstructorArgumentValues();
		argumentValues.addIndexedArgumentValue(0, newsListener);
		argumentValues.addIndexedArgumentValue(1, newsPersister);
		newsProvider.setConstructorArgumentValues(argumentValues);

		return beanRegistry;
	}

}