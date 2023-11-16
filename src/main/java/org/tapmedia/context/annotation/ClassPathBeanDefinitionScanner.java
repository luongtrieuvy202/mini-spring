package org.tapmedia.context.annotation;

import cn.hutool.core.util.StrUtil;
import org.tapmedia.beans.factory.config.BeanDefinition;
import org.tapmedia.beans.factory.support.BeanDefinitionRegistry;

import java.util.Set;

public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

	public static final String AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME = "org.tapmedia.context.annotation.internalAutowiredAnnotationProcessor";

	private BeanDefinitionRegistry registry;

	public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
		this.registry = registry;
		registry.registerBeanDefinition(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME,
				new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
	}

	public void doScan(String... basePackages) {
		for (String basePackage : basePackages) {
			Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
			for (BeanDefinition candidate : candidates) {
				String beanScope = resolveBeanScope(candidate);
				if (StrUtil.isNotEmpty(beanScope)) {
					candidate.setScope(beanScope);
				}

				String beanName = determineBeanName(candidate);
				registry.registerBeanDefinition(beanName, candidate);
			}
		}
	}

	private String resolveBeanScope(BeanDefinition beanDefinition) {
		Class<?> beanClass = beanDefinition.getBeanClass();
		Scope scope = beanClass.getAnnotation(Scope.class);
		if (scope != null) {
			return scope.value();
		}

		return StrUtil.EMPTY;
	}

	private String determineBeanName(BeanDefinition beanDefinition) {
		Class<?> beanClass = beanDefinition.getBeanClass();
		Component component = beanClass.getAnnotation(Component.class);
		String value = component.value();
		if (StrUtil.isEmpty(value)) {
			value = StrUtil.lowerFirst(beanClass.getSimpleName());
		}

		return value;
	}

}
