package org.tapmedia.aop;

import java.lang.reflect.Method;

public interface Pointcut {

	ClassFilter getClassFilter();

	MethodMatcher getMethodMatcher();

}
