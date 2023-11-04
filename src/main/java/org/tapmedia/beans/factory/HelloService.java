package org.tapmedia.beans.factory;

public class HelloService {

	private String foo;

	private String bar;

	public String getFoo() {
		return foo;
	}

	public String getBar() {
		return bar;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	public String sayHello() {
		System.out.println("hello");
		return "hello";
	}

	@Override
	public String toString() {
		return "HelloService{" + "foo='" + foo + '\'' + ", bar='" + bar + '\'' + '}';
	}

}
