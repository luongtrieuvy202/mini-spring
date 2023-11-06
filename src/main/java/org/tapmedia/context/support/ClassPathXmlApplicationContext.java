package org.tapmedia.context.support;

import org.tapmedia.beans.BeansException;

public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

	private String[] configLocations;

	public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
		this.configLocations = configLocations;
		refresh();
	}

	public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
		this(new String[] { configLocation });
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[0];
	}

}
