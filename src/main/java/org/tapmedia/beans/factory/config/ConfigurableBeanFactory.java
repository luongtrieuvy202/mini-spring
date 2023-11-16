package org.tapmedia.beans.factory.config;

import org.tapmedia.beans.factory.BeanFactory;
import org.tapmedia.beans.factory.HierarchicalBeanFactory;
import org.tapmedia.util.StringValueResolver;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

	void destroySingletons();

	void addEmbeddedValueResolver(StringValueResolver valueResolver);

	String resolveEmbeddedValue(String value);

}
