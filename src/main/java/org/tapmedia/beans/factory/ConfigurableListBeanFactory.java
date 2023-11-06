package org.tapmedia.beans.factory;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.config.AutowireCapableBeanFactory;
import org.tapmedia.beans.factory.config.BeanDefinition;
import org.tapmedia.beans.factory.config.BeanPostProcessor;
import org.tapmedia.beans.factory.config.ConfigurableBeanFactory;

import java.util.List;

public interface ConfigurableListBeanFactory
		extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

	BeanDefinition getBeanDefinition(String beanName) throws BeansException;

	void preInstantiateSingletons() throws BeansException;

	@Override
	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
