package com.jamp.io.model.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User extends Auditable {
	@Id 
	@GeneratedValue
	private long id;
	
	@Column(unique=true, nullable=false)
	private String name = "";

	@Column(nullable=false)
	private String password = "";
		
	public User() {
		super();
	}

	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public boolean isBlank() {
		return name.equals("") && password.equals("");
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
