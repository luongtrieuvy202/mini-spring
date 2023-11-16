package org.tapmedia.test.ioc;

import org.aspectj.apache.bcel.util.ClassPath;
import org.junit.Test;
import org.tapmedia.context.support.ClassPathXmlApplicationContext;
import org.tapmedia.test.ioc.bean.Car;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PackageScanTest {

	@Test
	public void testPackageScan() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:package-scan.xml");
		Car car = applicationContext.getBean("car", Car.class);
		assertThat(car).isNotNull();
	}

}
