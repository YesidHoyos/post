package com.woloxgram.post.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.woloxgram.post.util.exception.NotAllowedException;
import com.woloxgram.post.util.exception.CommentRestClientException;
import com.woloxgram.post.util.exception.PostRestClientException;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {PostRestClientException.class, CommentRestClientException.class})
	public ResponseEntity<Object> restClientException(RuntimeException excepcion, WebRequest request) {
		Map<String, String> response = new HashMap<>();
		response.put("message", excepcion.getMessage());
		response.put("cause", excepcion.getCause()==null?null:excepcion.getCause().getMessage());
        return handleExceptionInternal(excepcion, response, new HttpHeaders(), HttpStatus.BAD_GATEWAY, request);		
	}
	
	@ExceptionHandler(value = {NotAllowedException.class})
	public ResponseEntity<Object> notAllowedException(NotAllowedException excepcion, WebRequest request) {
		Map<String, String> response = new HashMap<>();
		response.put("message", excepcion.getMessage());
		response.put("cause", excepcion.getCause()==null?null:excepcion.getCause().getMessage());
        return handleExceptionInternal(excepcion, response, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED, request);		
	}
	
	@ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
	public ResponseEntity<Object> genericException(MethodArgumentTypeMismatchException excepcion, WebRequest request) {
		Map<String, String> response = new HashMap<>();
		response.put("message", excepcion.getMessage());
		response.put("cause", excepcion.getCause()==null?null:excepcion.getCause().getMessage());
        return handleExceptionInternal(excepcion, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);		
	}

}
