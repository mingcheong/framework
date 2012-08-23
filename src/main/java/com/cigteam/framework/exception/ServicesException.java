package com.cigteam.framework.exception;

/**
 * 服务层异常
 * 
 * @author Stone
 */
public class ServicesException extends Exception {

	private static final long serialVersionUID = -65235729218023400L;

	public ServicesException() {
		super();
	}

	public ServicesException(String message) {
		super(message);
	}

	public ServicesException(Throwable throwable) {
		super(throwable);
	}

	public ServicesException(String message, Throwable throwable) throws RuntimeException {
		super(message, throwable);
	}

}
