package org.tapmedia.beans.factory.config;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.BeanFactory;

import javax.enterprise.inject.spi.Bean;

public interface AutowireCapableBeanFactory extends BeanFactory {

	Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

	Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
