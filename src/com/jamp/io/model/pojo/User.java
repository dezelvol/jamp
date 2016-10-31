package com.jamp.io.model.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * Superclass for users
 * Aggregates main methods and fields through all application users
 */
@Entity
public class User implements Auditable {
	@Id 
	@GeneratedValue
	private long id;
	
	@Column(unique=true, nullable=false)
    @Size(min=2, max=50)
	private String name = "";

	@Column(nullable=false)
    @Size(min=3, max=50)
	private String password = "";
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdated;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@ManyToOne
	private User lastUpdatedBy;
	
	@ManyToOne
	private User createdBy;

	public User() {
		super();
	}
	
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(User lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
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
