package my.spring.demo.controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BindViaXmlFileNewsProvider {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
        BeanFactory container = bindViaXmlFile(beanRegistry);
        NewsProvider newsProvider = (NewsProvider)container.getBean("newsProvider");
        newsProvider.getAndPersistNews();
    }

    private static BeanFactory bindViaXmlFile(DefaultListableBeanFactory beanRegistry) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanRegistry);
        xmlBeanDefinitionReader.loadBeanDefinitions("spring-xml.xml");

        return beanRegistry;
    }

}
