package org.tapmedia.test.ioc.common;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.PropertyValue;
import org.tapmedia.beans.PropertyValues;
import org.tapmedia.beans.factory.ConfigurableListBeanFactory;
import org.tapmedia.beans.factory.config.BeanDefinition;
import org.tapmedia.beans.factory.config.BeanFactoryPostProcessor;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListBeanFactory beanFactory) throws BeansException {
		BeanDefinition personBeanDefinition = beanFactory.getBeanDefinition("person");
		PropertyValues propertyValues = personBeanDefinition.getPropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("name", "ivy"));
	}

}
