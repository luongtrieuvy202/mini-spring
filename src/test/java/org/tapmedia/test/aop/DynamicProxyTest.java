package org.tapmedia.test.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.tapmedia.aop.AdvisedSupport;
import org.tapmedia.aop.ClassFilter;
import org.tapmedia.aop.MethodMatcher;
import org.tapmedia.aop.TargetSource;
import org.tapmedia.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.tapmedia.aop.aspectj.AspectjExpressionPointcut;
import org.tapmedia.aop.framework.CglibAopProxy;
import org.tapmedia.aop.framework.JdkDynamicAopProxy;
import org.tapmedia.aop.framework.ProxyFactory;
import org.tapmedia.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.tapmedia.test.ioc.common.WorldServiceBeforeAdvice;
import org.tapmedia.test.ioc.common.WorldServiceInterceptor;
import org.tapmedia.test.ioc.service.WorldService;
import org.tapmedia.test.ioc.service.WorldServiceImpl;

public class DynamicProxyTest {

	private AdvisedSupport advisedSupport;

	@Before
	public void setup() {
		WorldService workService = new WorldServiceImpl();

		this.advisedSupport = new AdvisedSupport();
		TargetSource targetSource = new TargetSource(workService);
		WorldServiceInterceptor methodInterceptor = new WorldServiceInterceptor();
		MethodMatcher methodMatcher = new AspectjExpressionPointcut(
				"execution(* org.tapmedia.test.ioc.service.WorldService.explode(..))")
			.getMethodMatcher();
		advisedSupport.setTargetSource(targetSource);
		advisedSupport.setMethodInterceptor(methodInterceptor);
		advisedSupport.setMethodMatcher(methodMatcher);
	}

	@Test
	public void testJdkDynamicProxy() throws Exception {
		WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
		proxy.explode();
	}

	@Test
	public void testCglibDynamicProxy() throws Exception {
		WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
		proxy.explode();
	}

	@Test
	public void testProxyFactory() throws Exception {
		advisedSupport.setProxyTargetClass(false);
		WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
		proxy.explode();

		advisedSupport.setProxyTargetClass(true);
		proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
		proxy.explode();
	}

	@Test
	public void testBeforeAdvice() throws Exception {
		WorldServiceBeforeAdvice beforeAdvice = new WorldServiceBeforeAdvice();
		MethodBeforeAdviceInterceptor methodBeforeAdviceInterceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
		advisedSupport.setMethodInterceptor(methodBeforeAdviceInterceptor);

		WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
		proxy.explode();
	}

	@Test
	public void testAdvisor() throws Exception {
		WorldService worldService = new WorldServiceImpl();
		String expression = "execution(* org.tapmedia.test.ioc.service.WorldService.explode(..))";
		AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
		advisor.setExpression(expression);
		MethodBeforeAdviceInterceptor methodBeforeAdviceInterceptor = new MethodBeforeAdviceInterceptor(
				new WorldServiceBeforeAdvice());
		advisor.setAdvice(methodBeforeAdviceInterceptor);

		ClassFilter classFilter = advisor.getPointcut().getClassFilter();
		if (classFilter.matches(worldService.getClass())) {
			AdvisedSupport advisedSupport1 = new AdvisedSupport();
			TargetSource targetSource = new TargetSource(worldService);
			advisedSupport.setTargetSource(targetSource);
			advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
			WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
			proxy.explode();
		}
	}

}
