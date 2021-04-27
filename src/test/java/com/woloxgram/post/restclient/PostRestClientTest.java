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

import com.woloxgram.post.databuilder.PostTestDataBuilder;
import com.woloxgram.post.model.Post;
import com.woloxgram.post.util.restclient.impl.PostRestClient;

@SpringBootTest
class PostRestClientTest {
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private PostRestClient postRestClient;
	
	private static final String FIND_POSTS_ERROR = "Ocurrió un error al momento de consumir los datos de todos las publicaciones";
	private static final String FIND_BY_USER_ERROR = "Ocurrió un error al momento de consumir los datos de las publicaciones del usuario con id %s";
	
	@Test
	void getAllPostsSuccessfully() {
		//arrange
		List<Post> postsExpected = new ArrayList<>();
		Post post = new PostTestDataBuilder().build();
		postsExpected.add(post);
		ResponseEntity<List<Post>> response = new ResponseEntity<List<Post>>(postsExpected, HttpStatus.OK);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Post>>>any()))
		.thenReturn(response);
		
		//act
		List<Post> posts = postRestClient.getAllPosts();
		
		//assert
		Assertions.assertTrue(posts.contains(post));
	}
	
	@Test
	void getAllPostsWithEstatus404() {
		//arrange
		ResponseEntity<List<Post>> response = new ResponseEntity<List<Post>>(HttpStatus.NOT_FOUND);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Post>>>any()))
		.thenReturn(response);
		
		//act
		try {
			postRestClient.getAllPosts();
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(FIND_POSTS_ERROR, e.getMessage());
		}		
	}
	
	@Test
	void getPostsByUserSuccessfully() {
		//arrange
		List<Post> postsExpected = new ArrayList<>();
		Post post = new PostTestDataBuilder().build();
		postsExpected.add(post);
		ResponseEntity<List<Post>> response = new ResponseEntity<List<Post>>(postsExpected, HttpStatus.OK);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Post>>>any()))
		.thenReturn(response);
		
		//act
		List<Post> posts = postRestClient.getPostsByUser(1L);
		
		//assert
		Assertions.assertTrue(posts.contains(post));
	}
	
	@Test
	void getPostsByUserWithEstatus404() {
		//arrange
		ResponseEntity<List<Post>> response = new ResponseEntity<List<Post>>(HttpStatus.NOT_FOUND);
		when(restTemplate.exchange(ArgumentMatchers.anyString(),
		        ArgumentMatchers.any(HttpMethod.class),
		        ArgumentMatchers.any(),
		        ArgumentMatchers.<ParameterizedTypeReference<List<Post>>>any()))
		.thenReturn(response);
		
		//act
		try {
			postRestClient.getPostsByUser(1L);
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(String.format(FIND_BY_USER_ERROR, 1), e.getMessage());
		}		
	}
}