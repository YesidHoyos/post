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

import com.woloxgram.post.databuilder.CommentTestDataBuilder;
import com.woloxgram.post.databuilder.PostTestDataBuilder;
import com.woloxgram.post.model.Comment;
import com.woloxgram.post.model.Post;
import com.woloxgram.post.service.impl.CommentService;
import com.woloxgram.post.util.exception.CommentRestClientException;
import com.woloxgram.post.util.exception.PostRestClientException;
import com.woloxgram.post.util.restclient.impl.CommentRestClient;
import com.woloxgram.post.util.restclient.impl.PostRestClient;

@SpringBootTest
class CommentServiceTest {

	private static final String FIND_COMMENTS_BY_POST_ERROR = "Ocurrió un error al momento de consumir los datos de los comentarios de la publicación con id %s";
	private static final String FIND_BY_NAME_ERROR = "Ocurrió un error al momento de consumir los datos de los comentarios con nombre %s";
	private static final String FIND_POST_BY_USER_ERROR = "Ocurrió un error al momento de consumir los datos de las publicaciones del usuario con id %s";

	@Mock
	private CommentRestClient commentRestClient;

	@Mock
	private PostRestClient postRestClient;

	@InjectMocks
	private CommentService commentService;

	@Test
	void getCommentsByPostSuccessfully() {
		//arrange
		List<Comment> commentsExpected = new ArrayList<>();
		Comment comment = new CommentTestDataBuilder().build();
		commentsExpected.add(comment);
		when(commentRestClient.getCommentsByPost(ArgumentMatchers.anyLong())).thenReturn(commentsExpected);

		//act
		List<Comment> comments = commentService.getCommentsByPost(1L);

		//assert
		Assertions.assertTrue(comments.contains(comment));
	}
	
	@Test
	void getCommentsByPostWithError() {
		//arrange
		when(commentRestClient.getCommentsByPost(ArgumentMatchers.anyLong())).thenThrow(new CommentRestClientException(String.format(FIND_COMMENTS_BY_POST_ERROR, 1)));

		try {
			//act
			commentService.getCommentsByPost(1L);
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(String.format(FIND_COMMENTS_BY_POST_ERROR, 1), e.getMessage());
		}
	}

	@Test
	void getCommentsByUserSuccessfully() {
		//arrange
		List<Post> postsExpected = new ArrayList<>();
		Post post = new PostTestDataBuilder().build();
		postsExpected.add(post);
		List<Comment> commentsExpected = new ArrayList<>();
		Comment comment = new CommentTestDataBuilder().build();
		commentsExpected.add(comment);
		when(postRestClient.getPostsByUser(ArgumentMatchers.anyLong())).thenReturn(postsExpected);
		when(commentRestClient.getCommentsByPost(ArgumentMatchers.anyLong())).thenReturn(commentsExpected);

		//act
		List<Comment> comments = commentService.getCommentsByUser(1L);

		//assert
		Assertions.assertTrue(comments.contains(comment));
	}
	
	@Test
	void getCommentsByUserWithPostError() {
		//arrange
		List<Comment> commentsExpected = new ArrayList<>();
		Comment comment = new CommentTestDataBuilder().build();
		commentsExpected.add(comment);
		when(postRestClient.getPostsByUser(ArgumentMatchers.anyLong())).thenThrow(new PostRestClientException(String.format(FIND_POST_BY_USER_ERROR, 1)));
		when(commentRestClient.getCommentsByPost(ArgumentMatchers.anyLong())).thenReturn(commentsExpected);

		try {
			//act
			commentService.getCommentsByUser(1L);
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(String.format(FIND_POST_BY_USER_ERROR, 1), e.getMessage());
		}
	}
	
	@Test
	void getCommentsByUserWithCommentError() {
		//arrange
		List<Post> postsExpected = new ArrayList<>();
		Post post = new PostTestDataBuilder().build();
		postsExpected.add(post);
		when(postRestClient.getPostsByUser(ArgumentMatchers.anyLong())).thenReturn(postsExpected);
		when(commentRestClient.getCommentsByPost(ArgumentMatchers.anyLong())).thenThrow(new CommentRestClientException(String.format(FIND_COMMENTS_BY_POST_ERROR, 1)));

		try {
			//act
			commentService.getCommentsByPost(1L);
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(String.format(FIND_COMMENTS_BY_POST_ERROR, 1), e.getMessage());
		}
	}

	@Test
	void getCommentsByNameSuccessfully() {
		//arrange
		List<Comment> commentsExpected = new ArrayList<>();
		Comment comment = new CommentTestDataBuilder().build();
		commentsExpected.add(comment);
		when(commentRestClient.getCommentsByName(ArgumentMatchers.anyString())).thenReturn(commentsExpected);

		//act
		List<Comment> comments = commentService.getCommentsByName(comment.getName());

		//assert
		Assertions.assertTrue(comments.contains(comment));
	}
	
	@Test
	void getCommentsByNameWithError() {
		//arrange
		Comment comment = new CommentTestDataBuilder().build();
		when(commentRestClient.getCommentsByName(ArgumentMatchers.anyString())).thenThrow(new CommentRestClientException(String.format(FIND_BY_NAME_ERROR, comment.getName())));

		try {
			//act
			commentService.getCommentsByName(comment.getName());
			fail();
		} catch (Exception e) {
			//assert
			Assertions.assertEquals(String.format(FIND_BY_NAME_ERROR, comment.getName()), e.getMessage());
		}
	}

}
