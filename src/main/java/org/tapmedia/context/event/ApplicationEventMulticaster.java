package org.tapmedia.context.event;

import org.tapmedia.context.ApplicationEvent;
import org.tapmedia.context.ApplicationListener;

public interface ApplicationEventMulticaster {

	void addApplicationListener(ApplicationListener<?> listener);

	void removeApplicationListener(ApplicationListener<?> listener);

	void multicastEvent(ApplicationEvent event);

}
