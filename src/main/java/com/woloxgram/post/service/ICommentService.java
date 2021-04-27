package com.woloxgram.post.service;

import java.util.List;

import com.woloxgram.post.model.Comment;

public interface ICommentService {

	public List<Comment> getCommentsByPost(long postId);
	public List<Comment> getCommentsByUser(long userId);
	public List<Comment> getCommentsByName(String name);
}
