package org.uncodeframework.core.common.mail.spring;

public class MailException extends Exception {

	private static final long serialVersionUID = 1L;

	public MailException() {
		super();
	}

	public MailException(String message) {
		super(message);
	}

	public MailException(Throwable cause) {
		super(cause);
	}

	public MailException(String message, Throwable cause) {
		super(message, cause);
	}
}
