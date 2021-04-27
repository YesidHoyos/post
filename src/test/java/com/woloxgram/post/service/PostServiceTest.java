package com.woloxgram.post.service;

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

import com.woloxgram.post.databuilder.PostTestDataBuilder;
import com.woloxgram.post.model.Post;
import com.woloxgram.post.service.impl.PostService;
import com.woloxgram.post.util.exception.PostRestClientException;
import com.woloxgram.post.util.restclient.impl.PostRestClient;

@SpringBootTest
class PostServiceTest {

	@Mock
	private PostRestClient postRestClient;
	
	@InjectMocks
	private PostService postService;
	
	private static final String FIND_POSTS_ERROR = "Ocurrió un error al momento de consumir los datos de todos las publicaciones";
	private static final String FIND_BY_USER_ERROR = "Ocurrió un error al momento de consumir los datos de las publicaciones del usuario con id %s";
	
	@Test
	void getAllPostsSuccessfully() {
		//arrange
		List<Post> postsExpected = new ArrayList<>();
		Post post = new PostTestDataBuilder().build();
		postsExpected.add(post);
		when(postRestClient.getAllPosts()).thenReturn(postsExpected);
		
		//act
		List<Post> posts = postService.getAllPosts();
		
		//assert
		Assertions.assertTrue(posts.contains(post));
	}
	
	@Test
	void getAllPostsWithError() {
		//arrange
		when(postRestClient.getAllPosts()).thenThrow(new PostRestClientException(FIND_POSTS_ERROR));
		
		//act
		try {
			postService.getAllPosts();
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
		when(postRestClient.getPostsByUser(ArgumentMatchers.anyLong())).thenReturn(postsExpected);
		
		//act
		List<Post> posts = postService.getPostsByUser(1L);
		
		//assert
		Assertions.assertTrue(posts.contains(post));
	}
	
	@Test
	void getPostsByUserWithError() {
		//arrange
		when(postRestClient.getPostsByUser(ArgumentMatchers.anyLong())).thenThrow(new PostRestClientException(String.format(FIND_BY_USER_ERROR, 1)));
		
		//act
		try {
			postService.getPostsByUser(1L);
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(String.format(FIND_BY_USER_ERROR, 1), e.getMessage());
		}		
	}
	
}
