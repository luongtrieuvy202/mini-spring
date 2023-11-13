package org.tapmedia.test.aop;

import org.junit.Test;
import org.tapmedia.aop.aspectj.AspectjExpressionPointcut;
import org.tapmedia.test.ioc.service.HelloService;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
public class PointcutExpressionTest {
    @Test
    public void testPointcutExpression() throws Exception {
        AspectjExpressionPointcut pointcut = new AspectjExpressionPointcut("execution(* org.tapmedia.test.ioc.service.HelloService.*(..))");
        Class<HelloService> clazz = HelloService.class;
        Method method = clazz.getDeclaredMethod("sayHello");

        assertThat(pointcut.matches(clazz)).isTrue();
        assertThat(pointcut.matches(method, clazz)).isTrue();
    }

}
