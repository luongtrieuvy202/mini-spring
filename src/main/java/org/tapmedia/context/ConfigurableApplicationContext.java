package org.tapmedia.context;

import org.tapmedia.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {

	void refresh() throws BeansException;

	void close();

	void registerShutdownHook();

}
