package org.tapmedia.beans.factory;

import org.junit.Test;
import org.tapmedia.beans.PropertyValue;
import org.tapmedia.beans.PropertyValues;
import org.tapmedia.beans.factory.config.BeanDefinition;
import org.tapmedia.beans.factory.support.DefaultListableBeanFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class BeanFactoryTest {

	@Test
	public void testBeanFactory() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		PropertyValues propertyValues = new PropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("foo", "hello"));
		propertyValues.addPropertyValue(new PropertyValue("bar", "world"));
		BeanDefinition beanDefinition = new BeanDefinition(HelloService.class, propertyValues);
		beanFactory.registerBeanDefinition("helloService", beanDefinition);

		HelloService helloService = (HelloService) beanFactory.getBean("helloService");
		System.out.println(helloService.toString());
		assertThat(helloService.getBar()).isEqualTo("world");
		assertThat(helloService.getFoo()).isEqualTo("hello");
	}

}
