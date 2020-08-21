package com.algaworks.osworks.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrderServiceInputModel {
	
	private class ClientID {
		
		public ClientID() {}
		
		@NotNull
		private Long id;
		
		@SuppressWarnings("unused")
		public Long getId() {
			return id;
		}

		@SuppressWarnings("unused")
		public void setId(Long id) {
			this.id = id;
		}
	}
	
	@Valid
	@NotNull
	private ClientID client;
	
	@NotNull
	private BigDecimal price;
	
	@NotBlank
	private String description;
	
	// Getters
	public ClientID getClient() {
		return client;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}
	
	// Setters
	public void setClient(ClientID client) {
		this.client = client;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
