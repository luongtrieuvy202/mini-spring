package org.tapmedia.beans.factory.config;

import org.tapmedia.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {

	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
