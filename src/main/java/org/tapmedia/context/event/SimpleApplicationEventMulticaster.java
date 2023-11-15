package org.tapmedia.context.event;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.BeanFactory;
import org.tapmedia.context.ApplicationEvent;
import org.tapmedia.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

	public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
		setBeanFactory(beanFactory);
	}

	@Override
	public void multicastEvent(ApplicationEvent event) {
		for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
			if (supportsEvent(applicationListener, event)) {
				applicationListener.onApplicationEvent(event);
			}
		}
	}

	protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
		Type type = applicationListener.getClass().getGenericInterfaces()[0];
		Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
		String className = actualTypeArgument.getTypeName();
		Class<?> eventClassName;
		try {
			eventClassName = Class.forName(className);
		}
		catch (ClassNotFoundException ex) {
			throw new BeansException("wrong event class name: " + className);
		}

		return eventClassName.isAssignableFrom(event.getClass());
	}

}
