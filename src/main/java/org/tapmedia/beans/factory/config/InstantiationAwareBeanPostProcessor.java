package org.tapmedia.beans.factory.config;

import org.tapmedia.beans.BeansException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

	Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

}
