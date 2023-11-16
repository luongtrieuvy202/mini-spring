package org.tapmedia.beans.factory.support;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.BeanFactory;
import org.tapmedia.beans.factory.FactoryBean;
import org.tapmedia.beans.factory.config.BeanDefinition;
import org.tapmedia.beans.factory.config.BeanPostProcessor;
import org.tapmedia.beans.factory.config.ConfigurableBeanFactory;
import org.tapmedia.util.StringValueResolver;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

	private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

	private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

	private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

	@Override
	public Object getBean(String name) throws BeansException {
		Object sharedInstance = getSingleton(name);
		if (sharedInstance != null) {
			return getObjectForBeanInstance(sharedInstance, name);
		}

		BeanDefinition beanDefinition = getBeanDefinition(name);
		Object bean = createBean(name, beanDefinition);
		return getObjectForBeanInstance(bean, name);
	}

	protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

	protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

	@Override
	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
		this.beanPostProcessors.remove(beanPostProcessor);
		this.beanPostProcessors.add(beanPostProcessor);
	}

	@Override
	public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return (T) getBean(name);
	}

	public List<BeanPostProcessor> getBeanPostProcessors() {
		return this.beanPostProcessors;
	}

	protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
		Object object = beanInstance;
		if (beanInstance instanceof FactoryBean) {
			FactoryBean factoryBean = (FactoryBean) beanInstance;
			try {
				if (factoryBean.isSingleton()) {
					object = this.factoryBeanObjectCache.get(beanName);

					if (object == null) {
						object = factoryBean.getObject();
						this.factoryBeanObjectCache.put(beanName, object);
					}
				}
				else {
					object = factoryBean.getObject();
				}
			}
			catch (Exception ex) {
				throw new BeansException("FactoryBean threw exception on object [" + beanName + "]");
			}
		}

		return object;
	}

	public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
		this.embeddedValueResolvers.add(valueResolver);
	}

	public String resolveEmbeddedValue(String value) {
		String result = value;
		for (StringValueResolver resolver : this.embeddedValueResolvers) {
			result = resolver.resolveStringValue(value);
		}

		return result;
	}

}
