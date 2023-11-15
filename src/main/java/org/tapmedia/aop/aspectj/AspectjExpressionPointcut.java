package org.tapmedia.aop.aspectj;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.tapmedia.aop.ClassFilter;
import org.tapmedia.aop.MethodMatcher;
import org.tapmedia.aop.Pointcut;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class AspectjExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

	private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

	static {
		SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
	}

	private final PointcutExpression pointcutExpression;

	public AspectjExpressionPointcut(String expression) {
		PointcutParser pointcutParser = PointcutParser
			.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(
					SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
		pointcutExpression = pointcutParser.parsePointcutExpression(expression);
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return this;
	}

	@Override
	public ClassFilter getClassFilter() {
		return this;
	}

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
	}

	@Override
	public boolean matches(Class<?> clazz) {
		return pointcutExpression.couldMatchJoinPointsInType(clazz);
	}

}
