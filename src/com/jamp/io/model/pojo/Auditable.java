package com.jamp.io.model.pojo;

import java.util.Date;

import javax.persistence.Entity;

/**
 * Interface to provide basic methods that are needed to update main application bean's metadata
 */
public interface Auditable {
	
	public Date getLastUpdated();

	public void setLastUpdated(Date lastUpdated);

	public Date getCreated();

	public void setCreated(Date created);

	public User getLastUpdatedBy();

	public void setLastUpdatedBy(User lastUpdatedBy);

	public User getCreatedBy();

	public void setCreatedBy(User createdBy);

}
