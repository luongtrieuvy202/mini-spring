package org.tapmedia.test.ioc.common.event;

import org.tapmedia.context.ApplicationListener;
import org.tapmedia.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		System.out.println(this.getClass().getName());
	}

}
