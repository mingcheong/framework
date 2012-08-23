package com.cigteam.framework.exception;

/**
 * 控制层异常
 * 
 * @author Stone
 */
public class ActionException extends Exception {

	private static final long serialVersionUID = 8468118623639540327L;

	public ActionException() {
		super();
	}

	public ActionException(String message) {
		super(message);
	}

	public ActionException(Throwable throwable) {
		super(throwable);
	}

	public ActionException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
