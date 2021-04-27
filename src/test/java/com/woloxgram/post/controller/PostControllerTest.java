package com.woloxgram.post.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.woloxgram.post.databuilder.PostTestDataBuilder;
import com.woloxgram.post.model.Post;
import com.woloxgram.post.service.impl.PostService;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = PostController.class)
class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PostService postService;
	
	@Test
	void getAllPostTest() throws Exception {
		//arrange
		List<Post> posts = new ArrayList<>();
		Post post = new PostTestDataBuilder().build();
		posts.add(post);
		String bodyExpected = "[{\"id\":1,\"userId\":1,\"title\":\"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\"body\":\"quia et suscipit\\\\nsuscipit recusandae consequuntur expedita et cum\\\\nreprehenderit molestiae ut ut quas totam\\\\nnostrum rerum est autem sunt rem eveniet architecto\"}]";
		when(postService.getAllPosts()).thenReturn(posts);
		
		//act
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/posts")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		//assert
		Assertions.assertEquals(bodyExpected, result.getResponse().getContentAsString());
	}
	
	@Test
	void getPostsByUserTest() throws Exception {
		//arrange
		List<Post> posts = new ArrayList<>();
		Post post = new PostTestDataBuilder().build();
		posts.add(post);
		String bodyExpected = "[{\"id\":1,\"userId\":1,\"title\":\"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\"body\":\"quia et suscipit\\\\nsuscipit recusandae consequuntur expedita et cum\\\\nreprehenderit molestiae ut ut quas totam\\\\nnostrum rerum est autem sunt rem eveniet architecto\"}]";
		when(postService.getPostsByUser(ArgumentMatchers.anyLong())).thenReturn(posts);
		
		//act
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/posts?userId=1")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		//assert
		Assertions.assertEquals(bodyExpected, result.getResponse().getContentAsString());
	}
}
