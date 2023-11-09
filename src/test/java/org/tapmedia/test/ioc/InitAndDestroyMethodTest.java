package org.tapmedia.test.ioc;

import org.junit.Test;
import org.tapmedia.context.support.ClassPathXmlApplicationContext;

public class InitAndDestroyMethodTest {

	@Test
	public void testInitMethodAndDestroyMethod() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:init-and-destroy-method.xml");
		applicationContext.registerShutdownHook();
	}

}
