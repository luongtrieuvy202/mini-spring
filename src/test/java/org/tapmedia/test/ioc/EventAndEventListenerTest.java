package org.tapmedia.test.ioc;

import org.junit.Test;
import org.tapmedia.context.support.ClassPathXmlApplicationContext;
import org.tapmedia.test.ioc.common.event.CustomEvent;

public class EventAndEventListenerTest {

	@Test
	public void testEventListener() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:event-and-event-listener.xml");
		applicationContext.publishEvent(new CustomEvent(applicationContext));
		applicationContext.registerShutdownHook();
	}

}
