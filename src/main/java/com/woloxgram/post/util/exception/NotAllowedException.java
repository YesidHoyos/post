package com.woloxgram.post.util.exception;

public class NotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 6192316040253310720L;

	public NotAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotAllowedException(String message) {
		super(message);
	}
}
