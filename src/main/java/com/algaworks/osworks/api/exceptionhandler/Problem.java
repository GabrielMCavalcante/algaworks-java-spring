package com.algaworks.osworks.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problem {
	private Integer status;
	private LocalDateTime dateHour;
	private String title;
	private List<Field> fields;

	public static class Field {
		private String name;
		private String message;
		
		public Field(String name, String message) {
			super();
			this.name = name;
			this.message = message;
		}

		// Getters
		public String getName() {
			return name;
		}
		
		public String getMessage() {
			return message;
		}
		
		// Setters		
		public void setName(String name) {
			this.name = name;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
	
	// Getters
	public Integer getStatus() {
		return status;
	}
	
	public LocalDateTime getDateHour() {
		return dateHour;
	}
	
	public String getTitle() {
		return title;
	}
	
	public List<Field> getFields() {
		return fields;
	}
	
	// Setters
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public void setDateHour(LocalDateTime dateHour) {
		this.dateHour = dateHour;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
}
