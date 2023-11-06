package org.tapmedia.context.support;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

	private DefaultListableBeanFactory beanFactory;

	protected final void refreshBeanFactory() throws BeansException {
		DefaultListableBeanFactory beanFactory = createBeanFactory();
		loadBeanDefinitions(beanFactory);
		this.beanFactory = beanFactory;
	}

	protected DefaultListableBeanFactory createBeanFactory() {
		return new DefaultListableBeanFactory();
	}

	protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException;

	public DefaultListableBeanFactory getBeanFactory() {
		return beanFactory;
	}

}
