package com.woloxgram.post.restclient;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.woloxgram.post.databuilder.CommentTestDataBuilder;
import com.woloxgram.post.model.Comment;
import com.woloxgram.post.util.restclient.impl.CommentRestClient;


@SpringBootTest
class CommentRestClientTest {
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private CommentRestClient commentRestClient;
	
	private static final String FIND_BY_POST_ERROR = "Ocurrió un error al momento de consumir los datos de los comentarios de la publicación con id %s";
	private static final String FIND_BY_NAME_ERROR = "Ocurrió un error al momento de consumir los datos de los comentarios con nombre %s";
	

	@Test
	void getCommentsByPostSuccessfully() {
		//arrange
		List<Comment> commentsExpected = new ArrayList<>();
		Comment comment = new CommentTestDataBuilder().build();
		commentsExpected.add(comment);
		ResponseEntity<List<Comment>> response = new ResponseEntity<List<Comment>>(commentsExpected, HttpStatus.OK);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Comment>>>any()))
		.thenReturn(response);
		
		//act
		List<Comment> comments = commentRestClient.getCommentsByPost(1L);
		
		//assert
		Assertions.assertTrue(comments.contains(comment));
	}
	
	@Test
	void getCommentsByPostWithEstatus404() {
		//arrange
		ResponseEntity<List<Comment>> response = new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Comment>>>any()))
		.thenReturn(response);
		
		//act
		try {
			commentRestClient.getCommentsByPost(1L);
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(String.format(FIND_BY_POST_ERROR, 1), e.getMessage());
		}		
	}
	
	@Test
	void getCommentsByNameSuccessfully() {
		//arrange
		List<Comment> commentsExpected = new ArrayList<>();
		Comment comment = new CommentTestDataBuilder().build();
		commentsExpected.add(comment);
		ResponseEntity<List<Comment>> response = new ResponseEntity<List<Comment>>(commentsExpected, HttpStatus.OK);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Comment>>>any()))
		.thenReturn(response);
		
		//act
		List<Comment> comments = commentRestClient.getCommentsByName(comment.getName());
		
		//assert
		Assertions.assertTrue(comments.contains(comment));
	}
	
	@Test
	void getCommentsByNameWithEstatus404() {
		//arrange
		ResponseEntity<List<Comment>> response = new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Comment>>>any()))
		.thenReturn(response);
		
		//act
		try {
			commentRestClient.getCommentsByName("t");
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(String.format(FIND_BY_NAME_ERROR, "t"), e.getMessage());
		}		
	}
}