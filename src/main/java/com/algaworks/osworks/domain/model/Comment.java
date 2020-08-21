package com.algaworks.osworks.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private OrderService orderService;
	
	private String description;
	private OffsetDateTime sendDate;
	
	public Comment() {
		super();
	}
	
	public Comment(OrderService orderService, String description) {
		super();
		this.orderService = orderService;
		this.description = description;
	}

	// Getters
	public Long getId() {
		return id;
	}
	
	public OrderService getOrderService() {
		return orderService;
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
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setSendDate(OffsetDateTime sendDate) {
		this.sendDate = sendDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
