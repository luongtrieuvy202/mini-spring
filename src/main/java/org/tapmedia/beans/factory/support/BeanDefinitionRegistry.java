package org.tapmedia.beans.factory.support;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

	BeanDefinition getBeanDefinition(String beanName) throws BeansException;

	boolean containsBeanDefinition(String beanName);

	String[] getBeanDefinitionNames();

}
