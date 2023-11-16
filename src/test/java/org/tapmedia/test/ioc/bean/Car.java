package org.tapmedia.test.ioc.bean;

import org.tapmedia.context.annotation.Component;
import org.tapmedia.context.annotation.Value;

@Component
public class Car {

	@Value("${brand}")
	private String brand;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Car{" + "brand=" + brand + "\'" + "}";
	}

}
