package org.tapmedia.context;

import org.tapmedia.beans.BeansException;
import org.tapmedia.beans.factory.Aware;

public interface ApplicationContextAware extends Aware {

	void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
