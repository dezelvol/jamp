package com.jamp.io.utils.servicebeans;

import java.io.Serializable;

import com.jamp.io.enums.LoginEventType;

public class UserLoginEvent implements Serializable {
	public final LoginEventType eventType;
	public final String user;
	
	public UserLoginEvent(LoginEventType eventType, String user) {
		super();
		this.eventType = eventType;
		this.user = user;
	}
	
	@Override
	public String toString() {
		return this.eventType + this.user;
	}
}
