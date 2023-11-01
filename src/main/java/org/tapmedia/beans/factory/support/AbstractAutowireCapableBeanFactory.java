package org.tapmedia.beans.factory.support;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.config.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException{
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition){
        Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            bean = beanClass.newInstance();
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName,bean);
        return bean;
    }
}