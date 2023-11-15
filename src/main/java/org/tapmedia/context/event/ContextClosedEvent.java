package org.tapmedia.context.event;

import org.tapmedia.context.ApplicationContext;

public class ContextClosedEvent extends ApplicationContextEvent {

	public ContextClosedEvent(ApplicationContext source) {
		super(source);
	}

}
