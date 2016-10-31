package com.jamp.io.model.pojo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

/**
 * Plain student
 * Taking part in course only when has mentor assigned
 */
@Entity
public class Participant extends User {
	@Required
	@ManyToOne
	private Mentor mentor;

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}
}
