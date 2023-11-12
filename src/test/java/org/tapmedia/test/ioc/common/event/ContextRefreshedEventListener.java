package org.tapmedia.test.ioc.common.event;

import org.tapmedia.context.ApplicationListener;
import org.tapmedia.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println(this.getClass().getName());
	}

}
