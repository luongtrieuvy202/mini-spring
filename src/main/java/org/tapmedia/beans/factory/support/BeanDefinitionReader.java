package org.tapmedia.beans.factory.support;

import org.tapmedia.beans.BeansException;
import org.tapmedia.core.io.Resource;
import org.tapmedia.core.io.ResourceLoader;

public interface BeanDefinitionReader {

	BeanDefinitionRegistry getRegister();

	ResourceLoader getResourceLoader();

	void loadBeanDefinitions(Resource resource) throws BeansException;

	void loadBeanDefinitions(String location) throws BeansException;

	void loadBeanDefinitions(String[] locations) throws BeansException;

}
