package org.tapmedia.beans.factory.support;

import org.tapmedia.beans.BeansException;
import org.tapmedia.core.io.DefaultResourceLoader;
import org.tapmedia.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

	private final BeanDefinitionRegistry registry;

	private ResourceLoader resourceLoader;

	protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this(registry, new DefaultResourceLoader());
	}

	public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
		this.registry = registry;
		this.resourceLoader = resourceLoader;
	}

	@Override
	public BeanDefinitionRegistry getRegister() {
		return registry;
	}

	@Override
	public void loadBeanDefinitions(String[] locations) throws BeansException {
		for (String location : locations) {
			loadBeanDefinitions(location);
		}
	}

	@Override
	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

}
