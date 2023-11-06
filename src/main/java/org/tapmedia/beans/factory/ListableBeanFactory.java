package org.tapmedia.beans.factory;

import org.tapmedia.beans.BeansException;

import java.beans.Beans;
import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

	<T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

	String[] getBeanDefinitionNames();

}
