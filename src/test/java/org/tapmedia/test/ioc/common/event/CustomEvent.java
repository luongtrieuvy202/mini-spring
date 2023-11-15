package org.tapmedia.test.ioc.common.event;

import org.tapmedia.context.ApplicationContext;
import org.tapmedia.context.ApplicationEvent;
import org.tapmedia.context.event.ApplicationContextEvent;

public class CustomEvent extends ApplicationContextEvent {

	public CustomEvent(ApplicationContext source) {
		super(source);
	}

}
