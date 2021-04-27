package com.woloxgram.post.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
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

import com.woloxgram.post.databuilder.CommentTestDataBuilder;
import com.woloxgram.post.model.Comment;
import com.woloxgram.post.service.impl.CommentService;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = CommentController.class)
class CommentControllerTest {
	
	private static final String FILTER_NOT_FOUND = "No existe filtro de búsqueda";
	private static final String MORE_THAN_ONE_FILTER = "Existe más de un filtro de búsqueda";

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CommentService commentService;
	
	@Test
	void getCommentsByPostTest() throws Exception {
		//arrange
		List<Comment> comments = new ArrayList<>();
		Comment comment = new CommentTestDataBuilder().withPost(null).build();
		comments.add(comment);
		String bodyExpected = "[{\"id\":1,\"postId\":1,\"name\":\"id labore ex et quam laborum\",\"email\":\"Eliseo@gardner.biz\",\"body\":\"laudantium enim quasi est quidem magnam voluptate ipsam eos\\\\ntempora quo necessitatibus\\\\ndolor quam autem quasi\\\\nreiciendis et nam sapiente accusantium\"}]";
		when(commentService.getCommentsByPost(ArgumentMatchers.anyLong())).thenReturn(comments);
		when(commentService.getCommentsByUser(ArgumentMatchers.anyLong())).thenReturn(Collections.emptyList());
		when(commentService.getCommentsByName(ArgumentMatchers.anyString())).thenReturn(Collections.emptyList());
		
		//act
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/comments?postId=1")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		//assert
		Assertions.assertEquals(bodyExpected, result.getResponse().getContentAsString());
	}
	
	@Test
	void getCommentsByUserTest() throws Exception {
		//arrange
		List<Comment> comments = new ArrayList<>();
		Comment comment = new CommentTestDataBuilder().build();
		comments.add(comment);
		String bodyExpected = "[{\"id\":1,\"postId\":1,\"post\":{\"id\":1,\"userId\":1,\"title\":\"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\"body\":\"quia et suscipit\\\\nsuscipit recusandae consequuntur expedita et cum\\\\nreprehenderit molestiae ut ut quas totam\\\\nnostrum rerum est autem sunt rem eveniet architecto\"},\"name\":\"id labore ex et quam laborum\",\"email\":\"Eliseo@gardner.biz\",\"body\":\"laudantium enim quasi est quidem magnam voluptate ipsam eos\\\\ntempora quo necessitatibus\\\\ndolor quam autem quasi\\\\nreiciendis et nam sapiente accusantium\"}]";
		when(commentService.getCommentsByUser(ArgumentMatchers.anyLong())).thenReturn(comments);
		when(commentService.getCommentsByPost(ArgumentMatchers.anyLong())).thenReturn(Collections.emptyList());
		when(commentService.getCommentsByName(ArgumentMatchers.anyString())).thenReturn(Collections.emptyList());
		
		//act
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/comments?userId=1")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		//assert
		Assertions.assertEquals(bodyExpected, result.getResponse().getContentAsString());
	}
	
	@Test
	void getCommentsByNameTest() throws Exception {
		//arrange
		List<Comment> comments = new ArrayList<>();
		Comment comment = new CommentTestDataBuilder().withPost(null).build();
		comments.add(comment);
		String bodyExpected = "[{\"id\":1,\"postId\":1,\"name\":\"id labore ex et quam laborum\",\"email\":\"Eliseo@gardner.biz\",\"body\":\"laudantium enim quasi est quidem magnam voluptate ipsam eos\\\\ntempora quo necessitatibus\\\\ndolor quam autem quasi\\\\nreiciendis et nam sapiente accusantium\"}]";
		when(commentService.getCommentsByName(ArgumentMatchers.anyString())).thenReturn(comments);
		when(commentService.getCommentsByPost(ArgumentMatchers.anyLong())).thenReturn(Collections.emptyList());
		when(commentService.getCommentsByUser(ArgumentMatchers.anyLong())).thenReturn(Collections.emptyList());
		
		//act
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/comments?name=id%20labore%20ex%20et%20quam%20laborum")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		//assert
		Assertions.assertEquals(bodyExpected, result.getResponse().getContentAsString());
	}
	
	@Test
	void getCommentsWithoutFilter() throws Exception {
		//arrange
		when(commentService.getCommentsByPost(ArgumentMatchers.anyLong())).thenReturn(Collections.emptyList());
		when(commentService.getCommentsByUser(ArgumentMatchers.anyLong())).thenReturn(Collections.emptyList());
		when(commentService.getCommentsByName(ArgumentMatchers.anyString())).thenReturn(Collections.emptyList());
		
		try {
			//act
			mockMvc.perform(MockMvcRequestBuilders.get("/comments")
					.accept(MediaType.APPLICATION_JSON))
					.andReturn();
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertTrue(e.getMessage().contains(FILTER_NOT_FOUND));
		}
	}
	
	@Test
	void getCommentsWithMoreThanOneFilter() throws Exception {
		//arrange
		when(commentService.getCommentsByPost(ArgumentMatchers.anyLong())).thenReturn(Collections.emptyList());
		when(commentService.getCommentsByUser(ArgumentMatchers.anyLong())).thenReturn(Collections.emptyList());
		when(commentService.getCommentsByName(ArgumentMatchers.anyString())).thenReturn(Collections.emptyList());
		
		try {
			//act
			mockMvc.perform(MockMvcRequestBuilders.get("/comments?userId=1&postId=1")
					.accept(MediaType.APPLICATION_JSON))
					.andReturn();
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertTrue(e.getMessage().contains(MORE_THAN_ONE_FILTER));
		}
	}
}
