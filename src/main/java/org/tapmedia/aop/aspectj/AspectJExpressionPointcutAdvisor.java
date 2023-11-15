package org.tapmedia.aop.aspectj;

import org.aopalliance.aop.Advice;
import org.tapmedia.aop.Pointcut;
import org.tapmedia.aop.PointcutAdvisor;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

	private AspectjExpressionPointcut pointcut;

	private Advice advice;

	private String expression;

	@Override
	public Pointcut getPointcut() {
		return pointcut;
	}

	@Override
	public Advice getAdvice() {
		return advice;
	}

	public void setExpression(String expression) {
		this.expression = expression;
		pointcut = new AspectjExpressionPointcut(expression);
	}

	public void setAdvice(Advice advice) {
		this.advice = advice;
	}

}
