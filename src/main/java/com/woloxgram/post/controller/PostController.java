package com.woloxgram.post.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woloxgram.post.model.Post;
import com.woloxgram.post.service.IPostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	private IPostService postService;

	public PostController(IPostService postService) {
		this.postService = postService;
	}
	
	@GetMapping
	public List<Post> getPosts(@RequestParam(required = false) String userId) {
		if(userId == null)
			return postService.getAllPosts();
		else
			return postService.getPostsByUser(Long.parseLong(userId));
	}
}
