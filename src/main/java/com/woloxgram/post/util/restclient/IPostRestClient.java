package com.woloxgram.post.util.restclient;

import java.util.List;

import com.woloxgram.post.model.Post;

public interface IPostRestClient {

	public List<Post> getAllPosts();
	public List<Post> getPostsByUser(long userId);
}
