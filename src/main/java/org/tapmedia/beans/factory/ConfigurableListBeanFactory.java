package org.tapmedia.beans.factory;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.config.BeanDefinition;

import java.util.List;

public interface ConfigurableListBeanFactory extends ListableBeanFactory {

	BeanDefinition getBeanDefinition(String beanName) throws BeansException;

	void preInstantiateSingletons() throws Exception;

}
