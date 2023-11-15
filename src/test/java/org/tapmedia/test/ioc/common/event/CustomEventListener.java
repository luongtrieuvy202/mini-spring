package org.tapmedia.test.ioc.common.event;

import org.tapmedia.context.ApplicationListener;

public class CustomEventListener implements ApplicationListener<CustomEvent> {

	@Override
	public void onApplicationEvent(CustomEvent event) {
		System.out.println(this.getClass().getName());
	}

}
