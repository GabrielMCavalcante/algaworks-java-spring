package com.algaworks.osworks.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.osworks.domain.model.OrderServiceStatus;

public class OrderServiceModel {
	
	private Long id;
	private ClientModel client;
	private String description;
	private BigDecimal price;
	private OrderServiceStatus status;
	private OffsetDateTime openDate;
	private OffsetDateTime finishDate;
	
	// Getters
	public Long getId() {
		return id;
	}
	
	public ClientModel getClient() {
		return client;
	}
	
	public String getDescription() {
		return description;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public OrderServiceStatus getStatus() {
		return status;
	}
	
	public OffsetDateTime getOpenDate() {
		return openDate;
	}
	
	public OffsetDateTime getFinishDate() {
		return finishDate;
	}
	
	// Setters
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setClient(ClientModel client) {
		this.client = client;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public void setStatus(OrderServiceStatus status) {
		this.status = status;
	}
	
	public void setOpenDate(OffsetDateTime open_date) {
		this.openDate = open_date;
	}
	
	public void setFinishDate(OffsetDateTime finish_date) {
		this.finishDate = finish_date;
	}
	
	
	
}
