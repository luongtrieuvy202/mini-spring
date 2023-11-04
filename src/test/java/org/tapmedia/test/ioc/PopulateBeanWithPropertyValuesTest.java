package org.tapmedia.test.ioc;

import org.junit.Test;
import org.tapmedia.beans.PropertyValue;
import org.tapmedia.beans.PropertyValues;
import org.tapmedia.beans.factory.Person;
import org.tapmedia.beans.factory.config.BeanDefinition;
import org.tapmedia.beans.factory.support.DefaultListableBeanFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class PopulateBeanWithPropertyValuesTest {
    @Test
    public void testPopulateBeanWithPropertyValues() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "derek"));
        propertyValues.addPropertyValue(new PropertyValue("age", 18));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);
        beanFactory.registerBeanDefinition("person", beanDefinition);

        Person person = (Person) beanFactory.getBean("person");
        assertThat(person.getAge()).isEqualTo(18);
        assertThat(person.getName()).isEqualTo("derek");
        System.out.println(person);
    }
}
