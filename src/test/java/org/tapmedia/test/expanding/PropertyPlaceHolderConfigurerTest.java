package org.tapmedia.test.expanding;

import org.junit.Test;
import org.tapmedia.context.support.AbstractApplicationContext;
import org.tapmedia.context.support.ClassPathXmlApplicationContext;
import org.tapmedia.test.ioc.bean.Car;
import static org.assertj.core.api.Assertions.assertThat;

public class PropertyPlaceHolderConfigurerTest {

	@Test
	public void test() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:property-placeholder-configurer.xml");
		Car car = applicationContext.getBean("car", Car.class);
		assertThat(car.getBrand()).isEqualTo("lamborghini");
	}

}
