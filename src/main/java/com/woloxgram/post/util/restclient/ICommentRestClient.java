package com.woloxgram.post.util.restclient;

import java.util.List;

import com.woloxgram.post.model.Comment;

public interface ICommentRestClient {

	public List<Comment> getCommentsByPost(long postId);
	public List<Comment> getCommentsByName(String name);
}
