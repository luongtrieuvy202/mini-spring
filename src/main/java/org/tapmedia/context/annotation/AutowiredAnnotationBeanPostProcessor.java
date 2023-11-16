package org.tapmedia.context.annotation;

import cn.hutool.core.bean.BeanUtil;
import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.PropertyValues;
import org.tapmedia.beans.factory.BeanFactory;
import org.tapmedia.beans.factory.BeanFactoryAware;
import org.tapmedia.beans.factory.ConfigurableListBeanFactory;
import org.tapmedia.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.lang.reflect.Field;

public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

	private ConfigurableListBeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (ConfigurableListBeanFactory) beanFactory;
	}

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		return null;
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName)
			throws BeansException {
		Class<?> clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Value valueAnnotation = field.getAnnotation(Value.class);
			if (valueAnnotation != null) {
				String value = valueAnnotation.value();
				value = beanFactory.resolveEmbeddedValue(value);
				BeanUtil.setFieldValue(bean, field.getName(), value);
			}
		}

		return pvs;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return null;
	}

}
