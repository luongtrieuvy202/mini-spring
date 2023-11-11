package org.tapmedia.beans.factory;

import org.tapmedia.beans.BeansException;

public interface BeanFactoryAware extends Aware {

	void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
