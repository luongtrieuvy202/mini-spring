package org.tapmedia.aop;

import net.bytebuddy.implementation.bytecode.Throw;

import java.lang.reflect.Method;

public interface MethodBeforeAdvice extends BeforeAdvice {

	void before(Method method, Object[] args, Object target) throws Throwable;

}
