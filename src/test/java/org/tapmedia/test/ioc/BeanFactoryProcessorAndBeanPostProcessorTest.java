package org.tapmedia.test.ioc;

import org.junit.Test;
import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.ConfigurableListBeanFactory;
import org.tapmedia.beans.factory.config.BeanFactoryPostProcessor;
import org.tapmedia.beans.factory.support.DefaultListableBeanFactory;
import org.tapmedia.beans.factory.xml.XmlBeanDefinitionReader;
import org.tapmedia.test.ioc.bean.Car;
import org.tapmedia.test.ioc.bean.Person;
import org.tapmedia.test.ioc.common.CustomBeanFactoryPostProcessor;
import org.tapmedia.test.ioc.common.CustomerBeanPostProcessor;
import static org.assertj.core.api.Assertions.assertThat;

public class BeanFactoryProcessorAndBeanPostProcessorTest {

	@Test
	public void testBeanFactoryPostProcessor() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

		CustomBeanFactoryPostProcessor customBeanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
		customBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
		Person person = (Person) beanFactory.getBean("person");
		assertThat(person.getName()).isEqualTo("ivy");
	}

	@Test
	public void testBeanPostProcessor() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

		CustomerBeanPostProcessor customerBeanPostProcessor = new CustomerBeanPostProcessor();
		beanFactory.addBeanPostProcessor(customerBeanPostProcessor);

		Car car = (Car) beanFactory.getBean("car");
		System.out.println(car);
		assertThat(car.getBrand()).isEqualTo("lamborghini");
	}

}
