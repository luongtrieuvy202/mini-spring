package org.tapmedia.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.PropertyValue;
import org.tapmedia.beans.factory.config.AutowireCapableBeanFactory;
import org.tapmedia.beans.factory.config.BeanDefinition;
import org.tapmedia.beans.factory.config.BeanPostProcessor;
import org.tapmedia.beans.factory.config.BeanReference;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
		implements AutowireCapableBeanFactory {

	private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

	@Override
	protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
		return doCreateBean(beanName, beanDefinition);
	}

	protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
		Class beanClass = beanDefinition.getBeanClass();
		Object bean = null;
		try {
			bean = createBeanInstance(beanDefinition);
			applyPropertyValues(beanName, bean, beanDefinition);
			initializeBean(beanName, bean, beanDefinition);
		}
		catch (Exception e) {
			throw new BeansException("Instantiation of bean failed", e);
		}

		addSingleton(beanName, bean);
		return bean;
	}

	protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
		try {
			Class beanClass = beanDefinition.getBeanClass();
			for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
				String name = propertyValue.getName();
				Object value = propertyValue.getValue();
				if (value instanceof BeanReference) {
					BeanReference beanReference = (BeanReference) value;
					value = getBean(beanReference.getBeanName());
				}
				BeanUtil.setFieldValue(bean, name, value);
			}

		}
		catch (Exception ex) {
			throw new BeansException("Error setting property values for bean: " + beanName, ex);
		}
	}

	protected Object createBeanInstance(BeanDefinition beanDefinition) {
		return getInstantiationStrategy().instantiate(beanDefinition);
	}

	public InstantiationStrategy getInstantiationStrategy() {
		return instantiationStrategy;
	}

	public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
		this.instantiationStrategy = instantiationStrategy;
	}

	protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
		Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

		invokeInitMethods(beanName, wrappedBean, beanDefinition);

		wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
		return wrappedBean;
	}

	@Override
	public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
			throws BeansException {
		Object result = existingBean;
		for (BeanPostProcessor processor : getBeanPostProcessors()) {
			Object current = processor.postProcessBeforeInitialization(result, beanName);
			if (current == null) {
				return result;
			}

			result = current;
		}

		return result;
	}

	@Override
	public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
			throws BeansException {
		Object result = existingBean;
		for (BeanPostProcessor processor : getBeanPostProcessors()) {
			Object current = processor.postProcessAfterInitialization(result, beanName);
			if (current == null) {
				return result;
			}

			result = current;
		}

		return result;
	}

	protected void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) {
		System.out.println("invoke [" + beanName + "]");
	}

}
