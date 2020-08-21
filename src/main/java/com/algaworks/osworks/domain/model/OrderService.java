package com.algaworks.osworks.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "order_services")
@JsonInclude(Include.NON_NULL)
public class OrderService {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Client client;
	
	private String description;
	private BigDecimal price;
	
	@Enumerated(EnumType.STRING)
	private OrderServiceStatus status;
	
	private OffsetDateTime openDate;
	private OffsetDateTime finishDate;
	
	@OneToMany(mappedBy = "orderService")
	private List<Comment> comments = new ArrayList<Comment>();

	// Getters
	public Long getId() {
		return id;
	}
	
	public Client getClient() {
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
	
	public List<Comment> getComments() {
		return comments;
	}
	
	// Setters
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setClient(Client client) {
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
	
	public void setOpenDate(OffsetDateTime openDate) {
		this.openDate = openDate;
	}
	
	public void setFinishDate(OffsetDateTime finishDate) {
		this.finishDate = finishDate;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
		OrderService other = (OrderService) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
