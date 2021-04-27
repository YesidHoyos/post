package com.woloxgram.post.databuilder;

import com.woloxgram.post.model.Post;

public class PostTestDataBuilder {
	
	private static final long ID = 1L;
	private static final long USER_ID = 1L;
	private static final String TITLE = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
	private static final String BODY = "quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto";
	
	private long id;
	private long userId;
	private String title;
	private String body;
	
	public PostTestDataBuilder() {
		this.id = ID;
		this.userId = USER_ID;
		this.title = TITLE;
		this.body = BODY;
	}
	
	public Post build() {
		return new Post(this.id, this.userId, this.title, this.body);
	}
	
	public PostTestDataBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	public PostTestDataBuilder withUserId(Long userId) {
		this.userId = userId;
		return this;
	}
	public PostTestDataBuilder withTitle(String title) {
		this.title = title;
		return this;
	}
	public PostTestDataBuilder withBody(String body) {
		this.body = body;
		return this;
	}
}
