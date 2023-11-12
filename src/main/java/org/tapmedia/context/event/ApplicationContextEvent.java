package org.tapmedia.context.event;

import org.tapmedia.context.ApplicationContext;
import org.tapmedia.context.ApplicationEvent;

public abstract class ApplicationContextEvent extends ApplicationEvent {

	public ApplicationContextEvent(ApplicationContext source) {
		super(source);
	}

	public final ApplicationContext getApplicationContext() {
		return (ApplicationContext) getSource();
	}

}
