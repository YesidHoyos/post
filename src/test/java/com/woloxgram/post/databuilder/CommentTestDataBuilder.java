package com.woloxgram.post.databuilder;

import com.woloxgram.post.model.Comment;
import com.woloxgram.post.model.Post;

public class CommentTestDataBuilder {
	
	private static final long ID = 1L;
	private static final long POST_ID = 1L;
	private static final Post POST = new Post(1L, 1L, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto");
	private static final String NAME = "id labore ex et quam laborum";
	private static final String EMAIL = "Eliseo@gardner.biz";
	private static final String BODY = "laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium";

	private long id;
	private long postId;
	private Post post;
	private String name;
	private String email;
	private String body;
	
	public CommentTestDataBuilder() {
		this.id = ID;
		this.postId = POST_ID;
		this.post = POST;
		this.name = NAME;
		this.email = EMAIL;
		this.body = BODY;
	}
	
	public Comment build() {
		return new Comment(this.id, this.postId, this.post, this.name, this.email, this.body);
	}
	
	public CommentTestDataBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	public CommentTestDataBuilder withPostId(Long postId) {
		this.postId = postId;
		return this;
	}
	public CommentTestDataBuilder withPost(Post post) {
		this.post = post;
		return this;
	}
	public CommentTestDataBuilder withName(String name) {
		this.name = name;
		return this;
	}
	public CommentTestDataBuilder withEmail(String email) {
		this.email = email;
		return this;
	}
	public CommentTestDataBuilder withBody(String body) {
		this.body = body;
		return this;
	}
}
