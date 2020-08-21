package com.algaworks.osworks.api.model;

import java.time.OffsetDateTime;

public class CommentModel {
	
	private Long id;
	private String description;
	private OffsetDateTime sendDate;
	
	// Getters
	public Long getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public OffsetDateTime getSendDate() {
		return sendDate;
	}
	
	// Setters
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setSendDate(OffsetDateTime sendDate) {
		this.sendDate = sendDate;
	}
}
