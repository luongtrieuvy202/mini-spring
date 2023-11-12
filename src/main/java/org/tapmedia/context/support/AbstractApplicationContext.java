package org.tapmedia.context.support;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.ConfigurableListBeanFactory;
import org.tapmedia.beans.factory.config.BeanFactoryPostProcessor;
import org.tapmedia.beans.factory.config.BeanPostProcessor;
import org.tapmedia.beans.factory.config.ConfigurableBeanFactory;
import org.tapmedia.context.ApplicationEvent;
import org.tapmedia.context.ApplicationListener;
import org.tapmedia.context.ConfigurableApplicationContext;
import org.tapmedia.context.event.ApplicationEventMulticaster;
import org.tapmedia.context.event.ContextClosedEvent;
import org.tapmedia.context.event.ContextRefreshedEvent;
import org.tapmedia.context.event.SimpleApplicationEventMulticaster;
import org.tapmedia.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader
		implements ConfigurableApplicationContext {

	public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

	private ApplicationEventMulticaster applicationEventMulticaster;

	@Override
	public void refresh() throws BeansException {
		refreshBeanFactory();
		ConfigurableListBeanFactory beanFactory = getBeanFactory();

		beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

		invokeBeanFactoryPostProcessors(beanFactory);

		registerBeanPostProcessors(beanFactory);

		initApplicationEventMultiCaster();

		registerListeners();

		beanFactory.preInstantiateSingletons();

		finishRefresh();
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

	protected void initApplicationEventMultiCaster() {
		ConfigurableListBeanFactory beanFactory = getBeanFactory();
		applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
		beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
	}

	protected void registerListeners() {
		Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
		for (ApplicationListener applicationListener : applicationListeners) {
			applicationEventMulticaster.addApplicationListener(applicationListener);
		}
	}

	protected void finishRefresh() {
		publishEvent(new ContextRefreshedEvent(this));
	}

	public void publishEvent(ApplicationEvent event) {
		applicationEventMulticaster.multicastEvent(event);
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

		publishEvent(new ContextClosedEvent(this));
		destroyBeans();
	}

	protected void destroyBeans() {
		getBeanFactory().destroySingletons();
	}

}
