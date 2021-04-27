package com.woloxgram.post.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.woloxgram.post.model.Comment;
import com.woloxgram.post.model.Post;
import com.woloxgram.post.service.ICommentService;
import com.woloxgram.post.util.restclient.ICommentRestClient;
import com.woloxgram.post.util.restclient.IPostRestClient;

@Service
public class CommentService implements ICommentService {
	
	private ICommentRestClient commentRestClient;
	private IPostRestClient postRestClient;

	public CommentService(ICommentRestClient commentRestClient, IPostRestClient postRestClient) {
		this.commentRestClient = commentRestClient;
		this.postRestClient = postRestClient;
	}

	@Override
	public List<Comment> getCommentsByPost(long postId) {
		return commentRestClient.getCommentsByPost(postId);
	}

	@Override
	public List<Comment> getCommentsByUser(long userId) {
		List<Post> postsByUser = postRestClient.getPostsByUser(userId);
		List<Comment> commentsByUser = new LinkedList<>();
		postsByUser.stream().forEach(post -> {
			List<Comment> commentsByPost = commentRestClient.getCommentsByPost(post.getId());
			commentsByPost.forEach(comment -> comment.setPost(post));
			commentsByUser.addAll(commentsByPost);
		});
		return commentsByUser;
	}

	@Override
	public List<Comment> getCommentsByName(String name) {
		return commentRestClient.getCommentsByName(name);
	}

}
