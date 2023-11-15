package org.tapmedia.test.aop;

import org.junit.Test;
import org.tapmedia.context.support.ClassPathXmlApplicationContext;
import org.tapmedia.test.ioc.service.WorldService;

public class AutoProxyTest {
    @Test
    public void testAutoProxy() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");
        WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
        worldService.explode();
    }
}
