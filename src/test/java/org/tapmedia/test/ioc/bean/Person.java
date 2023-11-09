package org.tapmedia.test.ioc.bean;

import org.tapmedia.beans.factory.DisposableBean;
import org.tapmedia.beans.factory.InitializingBean;

public class Person implements InitializingBean, DisposableBean {

	private String name;

	private int age;

	@Override
	public void destroy() throws Exception {
		System.out.println("I died in the method name destroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("I was born in the method named afterPropertiesSet");
	}

	public void customInitMethod() {
		System.out.println("I was born in the method named customInitMethod");
	}

	public void customDestroyMethod() {
		System.out.println("I died in the method named customDestroyMethod");
	}

	private Car car;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Person{" + "name=" + name + '\'' + ", age=" + age + ", car" + car + "}";
	}

}
