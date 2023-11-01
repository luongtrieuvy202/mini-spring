package org.tapmedia.beans.factory.support;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.config.BeanDefinition;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
