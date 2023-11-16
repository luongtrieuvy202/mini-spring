package org.tapmedia.beans.factory;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.PropertyValue;
import org.tapmedia.beans.PropertyValues;
import org.tapmedia.beans.factory.config.BeanDefinition;
import org.tapmedia.beans.factory.config.BeanFactoryPostProcessor;
import org.tapmedia.core.io.DefaultResourceLoader;
import org.tapmedia.core.io.Resource;
import org.tapmedia.util.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

	public static final String PLACEHOLDER_PREFIX = "${";

	public static final String PLACEHOLDER_SUFFIX = "}";

	private String location;

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListBeanFactory beanFactory) throws BeansException {
		Properties properties = loadProperties();
		processProperties(beanFactory, properties);
		StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
		beanFactory.addEmbeddedValueResolver(valueResolver);
	}

	private Properties loadProperties() {
		try {
			DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
			Resource resource = resourceLoader.getResource(location);
			Properties properties = new Properties();
			properties.load(resource.getInputStream());
			return properties;
		}
		catch (IOException ex) {
			throw new BeansException("Could not load properties", ex);
		}
	}

	private void processProperties(ConfigurableListBeanFactory beanFactory, Properties properties)
			throws BeansException {
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		for (String beanName : beanDefinitionNames) {
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
			resolvePropertyValues(beanDefinition, properties);
		}
	}

	private void resolvePropertyValues(BeanDefinition beanDefinition, Properties properties) {
		PropertyValues propertyValues = beanDefinition.getPropertyValues();
		for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
			Object value = propertyValue.getValue();
			if (value instanceof String) {
				value = resolvePlaceholder((String) value, properties);
				propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));
			}
		}
	}

	private String resolvePlaceholder(String value, Properties properties) {
		String strVal = value;
		StringBuffer buf = new StringBuffer(strVal);
		int startIndex = strVal.indexOf(PLACEHOLDER_PREFIX);
		int endIndex = strVal.indexOf(PLACEHOLDER_SUFFIX);
		if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
			String propKey = strVal.substring(startIndex + 2, endIndex);
			String propVal = properties.getProperty(propKey);
			buf.replace(startIndex, endIndex + 1, propVal);
		}
		return buf.toString();
	}

	private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

		private final Properties properties;

		public PlaceholderResolvingStringValueResolver(Properties properties) {
			this.properties = properties;
		}

		@Override
		public String resolveStringValue(String strVal) {
			return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal, properties);
		}

	}

}
