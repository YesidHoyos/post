package com.woloxgram.post.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.woloxgram.post.model.Post;
import com.woloxgram.post.service.IPostService;
import com.woloxgram.post.util.restclient.IPostRestClient;

@Service
public class PostService implements IPostService {

	private IPostRestClient postRestClient;
	
	public PostService(IPostRestClient postRestClient) {
		this.postRestClient = postRestClient;
	}

	@Override
	public List<Post> getAllPosts() {
		return postRestClient.getAllPosts();
	}

	@Override
	public List<Post> getPostsByUser(long userId) {
		return postRestClient.getPostsByUser(userId);
	}

}
