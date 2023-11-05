package org.tapmedia.test.ioc.common;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.config.BeanPostProcessor;
import org.tapmedia.test.ioc.bean.Car;

public class CustomerBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("CustomerBeanPostProcessor#postProcessBeforeInitialization");
		if ("car".equals(beanName)) {
			((Car) bean).setBrand("lamborghini");
		}

		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("CustomerBeanPostProcessor#postProcessAfterInitialization");
		return bean;
	}

}
