package org.tapmedia.beans.factory.config;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.ConfigurableListBeanFactory;

public interface BeanFactoryPostProcessor {

	void postProcessBeanFactory(ConfigurableListBeanFactory beanFactory) throws BeansException;

}