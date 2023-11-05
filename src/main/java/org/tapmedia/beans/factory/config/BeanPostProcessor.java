package org.tapmedia.beans.factory.config;

import org.tapmedia.beans.BeansException;

public interface BeanPostProcessor {

	Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

	Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
