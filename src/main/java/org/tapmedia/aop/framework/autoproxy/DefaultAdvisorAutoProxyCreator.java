package org.tapmedia.aop.framework.autoproxy;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.tapmedia.aop.*;
import org.tapmedia.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.tapmedia.aop.framework.ProxyFactory;
import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.PropertyValues;
import org.tapmedia.beans.factory.BeanFactory;
import org.tapmedia.beans.factory.BeanFactoryAware;
import org.tapmedia.beans.factory.config.BeanDefinition;
import org.tapmedia.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.tapmedia.beans.factory.support.DefaultListableBeanFactory;

import java.lang.annotation.Target;
import java.util.Collection;

public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

	private DefaultListableBeanFactory beanFactory;

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if (isInfrastructureClass(beanClass)) {
			return null;
		}

		Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory
			.getBeansOfType(AspectJExpressionPointcutAdvisor.class)
			.values();
		try {
			for (AspectJExpressionPointcutAdvisor advisor : advisors) {
				ClassFilter classFilter = advisor.getPointcut().getClassFilter();
				if (classFilter.matches(beanClass)) {
					AdvisedSupport advisedSupport = new AdvisedSupport();
					BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
					Object bean = beanFactory.getInstantiationStrategy().instantiate(beanDefinition);
					TargetSource targetSource = new TargetSource(bean);
					advisedSupport.setTargetSource(targetSource);
					advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
					advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

					return new ProxyFactory(advisedSupport).getProxy();
				}
			}
		}
		catch (Exception ex) {
			throw new BeansException("Error create proxy bean for: " + beanName, ex);
		}

		return null;
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName)
			throws BeansException {
		return pvs;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (DefaultListableBeanFactory) beanFactory;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	private boolean isInfrastructureClass(Class<?> beanClass) {
		return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass)
				|| Advisor.class.isAssignableFrom(beanClass);
	}

}
