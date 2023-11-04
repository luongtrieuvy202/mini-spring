package org.tapmedia.beans.factory.support;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.config.BeanDefinition;

public class CglibSubclassInstantiationStrategy implements InstantiationStrategy {

	@Override
	public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
		throw new UnsupportedOperationException("CGLIB instantiation strategy is not supported");
	}

}
