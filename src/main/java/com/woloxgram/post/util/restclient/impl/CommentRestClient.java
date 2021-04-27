package com.woloxgram.post.util.restclient.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.woloxgram.post.model.Comment;
import com.woloxgram.post.util.restclient.ICommentRestClient;
import com.woloxgram.post.util.exception.CommentRestClientException;

@Component
public class CommentRestClient implements ICommentRestClient {

	private static final String FIND_BY_POST_URL = "https://jsonplaceholder.typicode.com/comments?postId=%s";
	private static final String FIND_BY_POST_ERROR = "Ocurrió un error al momento de consumir los datos de los comentarios de la publicación con id %s";
	private static final String FIND_BY_NAME_URL = "https://jsonplaceholder.typicode.com/comments?name=%s";
	private static final String FIND_BY_NAME_ERROR = "Ocurrió un error al momento de consumir los datos de los comentarios con nombre %s";
	
	private RestTemplate restTemplate;
	
	public CommentRestClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Comment> getCommentsByPost(long postId) {
		ResponseEntity<List<Comment>> response = null;
		try {
			response = restTemplate.
					exchange(String.format(FIND_BY_POST_URL, postId), HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>(){});
		} catch (RestClientException e) {
			throw new CommentRestClientException(String.format(FIND_BY_POST_ERROR, postId), e);
		}
		
		if(response.getStatusCode() != HttpStatus.OK) {
			throw new CommentRestClientException(String.format(FIND_BY_POST_ERROR, postId));
		}
		
		return response.getBody();
	}

	@Override
	public List<Comment> getCommentsByName(String name) {
		ResponseEntity<List<Comment>> response = null;
		try {
			response = restTemplate.
					exchange(String.format(FIND_BY_NAME_URL, name), HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>(){});
		} catch (RestClientException e) {
			throw new CommentRestClientException(String.format(FIND_BY_NAME_ERROR, name), e);
		}
		
		if(response.getStatusCode() != HttpStatus.OK) {
			throw new CommentRestClientException(String.format(FIND_BY_NAME_ERROR, name));
		}
		
		return response.getBody();
	}

}
