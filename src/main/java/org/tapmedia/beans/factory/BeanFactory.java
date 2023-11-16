package org.tapmedia.beans.factory;

import org.tapmedia.beans.BeansException;

import javax.enterprise.inject.spi.Bean;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public interface BeanFactory {

	/**
	 * Get Bean
	 * @param name
	 * @return
	 * @throws BeansException
	 */
	Object getBean(String name) throws BeansException;

	<T> T getBean(String name, Class<T> requiredType) throws BeansException;

	<T> T getBean(Class<T> requiredType) throws BeansException;

}
