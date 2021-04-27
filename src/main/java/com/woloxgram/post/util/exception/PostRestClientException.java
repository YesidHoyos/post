package com.woloxgram.post.util.exception;

public class PostRestClientException extends RuntimeException {

	private static final long serialVersionUID = 4144515889117769470L;

	public PostRestClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public PostRestClientException(String message) {
		super(message);
	}
}
