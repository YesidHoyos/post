package com.woloxgram.post.util.restclient.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.woloxgram.post.model.Post;
import com.woloxgram.post.util.restclient.IPostRestClient;
import com.woloxgram.post.util.restclient.exception.PostRestClientException;

@Component
public class PostRestClient implements IPostRestClient {
	
	private static final String FIND_ALL_URL = "https://jsonplaceholder.typicode.com/posts";
	private static final String FIND_POSTS_ERROR = "Ocurrió un error al momento de consumir los datos de todos las publicaciones";
	private static final String FIND_BY_USER_URL = "https://jsonplaceholder.typicode.com/posts?userId=%s";
	private static final String FIND_BY_USER_ERROR = "Ocurrió un error al momento de consumir los datos de las publicaciones del usuario con id %s";
	
	private RestTemplate restTemplate;

	public PostRestClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Post> getAllPosts() {
		ResponseEntity<List<Post>> response = null;
		try {
			response = restTemplate.
					exchange(FIND_ALL_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>(){});
		} catch (RestClientException e) {
			throw new PostRestClientException(FIND_POSTS_ERROR, e);
		}
		
		if(response.getStatusCode() != HttpStatus.OK) {
			throw new PostRestClientException(FIND_POSTS_ERROR);
		}
		
		return response.getBody();
	}

	@Override
	public List<Post> getPostsByUser(long userId) {
		ResponseEntity<List<Post>> response = null;
		try {
			response = restTemplate.
					exchange(String.format(FIND_BY_USER_URL, userId), HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>(){});
		} catch (RestClientException e) {
			throw new PostRestClientException(String.format(FIND_BY_USER_ERROR, userId), e);
		}
		
		if(response.getStatusCode() != HttpStatus.OK) {
			throw new PostRestClientException(String.format(FIND_BY_USER_ERROR, userId));
		}
		
		return response.getBody();
	}

}
