package com.woloxgram.post.util.exception;

public class CommentRestClientException extends RuntimeException {

	private static final long serialVersionUID = 4144515889117769470L;

	public CommentRestClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommentRestClientException(String message) {
		super(message);
	}
}
