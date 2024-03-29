package org.tapmedia.test.ioc;

import org.junit.Test;
import org.tapmedia.context.support.ClassPathXmlApplicationContext;
import org.tapmedia.test.ioc.bean.Car;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoryBeanTest {

	@Test
	public void testFactoryBean() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:factory-bean.xml");
		Car car = applicationContext.getBean("car", Car.class);
		assertThat(car.getBrand()).isEqualTo("porsche");
	}

}
