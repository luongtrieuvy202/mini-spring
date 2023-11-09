package org.tapmedia.beans.factory;

import org.tapmedia.beans.BeansException;

public interface DisposableBean {

	void destroy() throws Exception;

}
