package com.jamp.io.model.pojo;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Mentor - could have many mentees 
 */
@Entity
public class Mentor extends User {

	@OneToMany(targetEntity=Participant.class, mappedBy="mentor", cascade=CascadeType.REMOVE)
	private Collection<Participant> mentees = new ArrayList<>();

	public Mentor(String string, String string2) {
		super(string, string2);
	}
	
	public Mentor() {
		super();
	}
	
	public Collection<Participant> getMentees() {
		return mentees;
	}

	public void setMentees(Collection<Participant> participants) {
		mentees = participants;
	}
}
