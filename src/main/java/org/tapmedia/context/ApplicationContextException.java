package org.tapmedia.context;

import org.tapmedia.beans.BeansException;

public class ApplicationContextException extends BeansException {

	public ApplicationContextException(String msg) {
		super(msg);
	}

	public ApplicationContextException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
