package com.algaworks.osworks.api.model;

public class CommentsInputModel {
	
	private String description;
	
	private Long comment_id;

	// Getters
	public Long getComment_id() {
		return comment_id;
	}
	
	public String getDescription() {
		return description;
	}
	
	// Setters
	public void setComment_id(Long comment_id) {
		this.comment_id = comment_id;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
