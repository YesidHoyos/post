package com.woloxgram.post.service;

import java.util.List;

import com.woloxgram.post.model.Post;

public interface IPostService {

	public List<Post> getAllPosts();
	public List<Post> getPostsByUser(long userId);
}
