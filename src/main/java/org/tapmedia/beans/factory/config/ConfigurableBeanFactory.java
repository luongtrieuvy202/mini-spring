package org.tapmedia.beans.factory.config;

import org.tapmedia.beans.factory.BeanFactory;
import org.tapmedia.beans.factory.HierarchicalBeanFactory;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

	void destroySingletons();

}
