package com.cigteam.framework.exception;

/**
 * 持久层异常
 * 
 * @author Stone
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = 5888714246960117200L;

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable throwable) {
		super(throwable);
	}

	public DaoException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
