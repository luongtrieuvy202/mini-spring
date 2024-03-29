package org.tapmedia.context.annotation;

import cn.hutool.core.util.ClassUtil;
import org.tapmedia.beans.factory.config.BeanDefinition;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class ClassPathScanningCandidateComponentProvider {

	public Set<BeanDefinition> findCandidateComponents(String basePackage) {
		Set<BeanDefinition> candidates = new LinkedHashSet<>();
		Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
		for (Class<?> clazz : classes) {
			BeanDefinition beanDefinition = new BeanDefinition(clazz);
			candidates.add(beanDefinition);
		}
		return candidates;
	}

}
