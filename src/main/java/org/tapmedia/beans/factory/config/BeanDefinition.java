package org.tapmedia.beans.factory.config;

import org.tapmedia.beans.PropertyValues;

public class BeanDefinition {

	public static String SCOPE_SINGLETON = "singleton";

	public static String SCOPE_PROTOTYPE = "prototype";

	private Class beanClass;

	private PropertyValues propertyValues;

	private String scope = SCOPE_SINGLETON;

	private boolean singleton = true;

	private boolean prototype = false;

	public BeanDefinition(Class beanClass) {
		this(beanClass, null);
	}

	public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
		this.beanClass = beanClass;
		this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
	}

	private String initMethodName;

	private String destroyMethodName;

	public String getInitMethodName() {
		return initMethodName;
	}

	public void setInitMethodName(String initMethodName) {
		this.initMethodName = initMethodName;
	}

	public String getDestroyMethodName() {
		return destroyMethodName;
	}

	public void setDestroyMethodName(String destroyMethodName) {
		this.destroyMethodName = destroyMethodName;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}

	public void setScope(String scope) {
		this.scope = scope;
		this.singleton = SCOPE_SINGLETON.equals(scope);
		this.prototype = SCOPE_PROTOTYPE.equals(scope);
	}

	public boolean isSingleton() {
		return this.singleton;
	}

	public boolean isPrototype() {
		return this.prototype;
	}

}
