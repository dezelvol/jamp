package com.jamp.io.model.pojo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Participant extends User {
	@ManyToOne
	private Mentor mentor;

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}
}
