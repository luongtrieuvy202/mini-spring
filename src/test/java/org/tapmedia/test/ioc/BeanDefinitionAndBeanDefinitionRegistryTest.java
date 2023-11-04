package org.tapmedia.test.ioc;

import org.junit.Test;
import org.tapmedia.beans.PropertyValue;
import org.tapmedia.beans.PropertyValues;
import org.tapmedia.beans.factory.Person;
import org.tapmedia.beans.factory.config.BeanDefinition;
import org.tapmedia.beans.factory.support.DefaultListableBeanFactory;
import org.tapmedia.test.ioc.service.HelloService;

import static org.assertj.core.api.Assertions.assertThat;

public class BeanDefinitionAndBeanDefinitionRegistryTest {

	@Test
	public void testGetBean() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
		beanFactory.registerBeanDefinition("helloService", beanDefinition);

		HelloService helloService = (HelloService) beanFactory.getBean("helloService");
		assertThat(helloService).isNotNull();
		helloService.sayHello();
	}

}
