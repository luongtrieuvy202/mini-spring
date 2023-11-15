package org.tapmedia.test.aop;

import org.junit.Test;
import org.tapmedia.aop.AdvisedSupport;
import org.tapmedia.aop.MethodMatcher;
import org.tapmedia.aop.TargetSource;
import org.tapmedia.aop.aspectj.AspectjExpressionPointcut;
import org.tapmedia.aop.framework.JdkDynamicAopProxy;
import org.tapmedia.test.ioc.common.WorldServiceInterceptor;
import org.tapmedia.test.ioc.service.WorldService;
import org.tapmedia.test.ioc.service.WorldServiceImpl;

public class DynamicProxyTest {

	@Test
	public void testJdkDynamicProxy() throws Exception {
		WorldService workService = new WorldServiceImpl();

		AdvisedSupport advisedSupport = new AdvisedSupport();
		TargetSource targetSource = new TargetSource(workService);
		WorldServiceInterceptor methodInterceptor = new WorldServiceInterceptor();
		MethodMatcher methodMatcher = new AspectjExpressionPointcut(
				"execution(* org.tapmedia.test.ioc.service.WorldService.explode(..))")
			.getMethodMatcher();
		advisedSupport.setTargetSource(targetSource);
		advisedSupport.setMethodInterceptor(methodInterceptor);
		advisedSupport.setMethodMatcher(methodMatcher);
		WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
		proxy.explode();
	}

}
