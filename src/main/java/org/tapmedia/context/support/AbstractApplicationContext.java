package org.tapmedia.context.support;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.ConfigurableListBeanFactory;
import org.tapmedia.beans.factory.config.BeanFactoryPostProcessor;
import org.tapmedia.beans.factory.config.BeanPostProcessor;
import org.tapmedia.beans.factory.config.ConfigurableBeanFactory;
import org.tapmedia.context.ConfigurableApplicationContext;
import org.tapmedia.core.io.DefaultResourceLoader;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader
		implements ConfigurableApplicationContext {

	@Override
	public void refresh() throws BeansException {
		refreshBeanFactory();
		ConfigurableListBeanFactory beanFactory = getBeanFactory();

		invokeBeanFactoryPostProcessors(beanFactory);

		registerBeanPostProcessors(beanFactory);

		beanFactory.preInstantiateSingletons();
	}

	protected abstract void refreshBeanFactory() throws BeansException;

	protected void invokeBeanFactoryPostProcessors(ConfigurableListBeanFactory beanFactory) {
		Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory
			.getBeansOfType(BeanFactoryPostProcessor.class);
		for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
			beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
		}

	}

	protected void registerBeanPostProcessors(ConfigurableListBeanFactory beanFactory) {
		Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);

		for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
			beanFactory.addBeanPostProcessor(beanPostProcessor);
		}
	}

	@Override
	public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return getBeanFactory().getBean(name, requiredType);
	}

	@Override
	public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
		return getBeanFactory().getBeansOfType(type);
	}

	@Override
	public String[] getBeanDefinitionNames() {
		return getBeanFactory().getBeanDefinitionNames();
	}

	public Object getBean(String name) throws BeansException {
		return getBeanFactory().getBean(name);
	}

	public String[] getDefinitionsNames() {
		return getBeanFactory().getBeanDefinitionNames();
	}

	public abstract ConfigurableListBeanFactory getBeanFactory();

	public void close() {
		doClose();
	}

	public void registerShutdownHook() {
		Thread shutdownHook = new Thread() {
			public void run() {
				doClose();
			}
		};
		Runtime.getRuntime().addShutdownHook(shutdownHook);
	}

	protected void doClose() {
		destroyBeans();
	}

	protected void destroyBeans() {
		getBeanFactory().destroySingletons();
	}

}
