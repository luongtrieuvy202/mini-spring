package org.tapmedia.test.ioc;

import org.junit.Test;
import org.tapmedia.context.support.ClassPathXmlApplicationContext;
import org.tapmedia.test.ioc.bean.Car;

import static org.assertj.core.api.Assertions.assertThat;

public class ValueAnnotationTest {

	@Test
	public void testValueAnnotation() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:value-annotation.xml");
		Car car = applicationContext.getBean("car", Car.class);
		assertThat(car).isNotNull();
		assertThat(car.getBrand()).isEqualTo("lamborghini");
	}

}
