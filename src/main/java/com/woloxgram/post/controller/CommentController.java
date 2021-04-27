package com.woloxgram.post.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woloxgram.post.model.Comment;
import com.woloxgram.post.service.ICommentService;
import com.woloxgram.post.util.exception.NotAllowedException;

@RestController
@RequestMapping("/comments")
public class CommentController {
	
	private static final String FILTER_NOT_FOUND = "No existe filtro de búsqueda";
	private static final String MORE_THAN_ONE_FILTER = "Existe más de un filtro de búsqueda";
	private ICommentService commentService;

	public CommentController(ICommentService commentService) {
		this.commentService = commentService;
	}


	@GetMapping
	public List<Comment> getComments(@RequestParam(required = true) Map<String, String> params) {
		if(params.size() > 1) {
			throw new NotAllowedException(MORE_THAN_ONE_FILTER);
		}
		String postId = params.get("postId");
		String userId = params.get("userId");
		String name = params.get("name");
		if(postId != null) 
			return commentService.getCommentsByPost(Long.parseLong(postId));
		else if(userId != null)
			return commentService.getCommentsByUser(Long.parseLong(userId));
		else if(name != null)
			return commentService.getCommentsByName(name);
		else
			throw new NotAllowedException(FILTER_NOT_FOUND);
	}
}
