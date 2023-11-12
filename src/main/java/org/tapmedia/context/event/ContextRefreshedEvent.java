package org.tapmedia.context.event;

import org.tapmedia.context.ApplicationContext;

public class ContextRefreshedEvent extends ApplicationContextEvent {

	public ContextRefreshedEvent(ApplicationContext source) {
		super(source);
	}

}
