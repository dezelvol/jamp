package com.jamp.io.model.pojo;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Mentor extends User {

	@OneToMany(targetEntity=Participant.class, mappedBy="mentor")
	private Collection<Participant> mentees;

	public Collection<Participant> getMentees() {
		return mentees;
	}

	public void setMentees(Collection<Participant> participants) {
		mentees = participants;
	}
}
