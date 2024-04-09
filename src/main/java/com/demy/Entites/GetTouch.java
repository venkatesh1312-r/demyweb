package com.demy.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="get_touch")
public class GetTouch {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	private String name;
    private String contact;
    private String email;
    private String message;
    
    
	    @Override
	public String toString() {
		return "GetTouch [id=" + id + ", name=" + name + ", contact=" + contact + ", email=" + email + ", message="
				+ message + "]";
	}
		public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
		
}
