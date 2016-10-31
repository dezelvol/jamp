package com.jamp.io.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jamp.io.enums.LoginEventType;
import com.jamp.io.jms.JmsMessageProducer;
import com.jamp.io.model.pojo.Mentor;
import com.jamp.io.model.pojo.SessionData;
import com.jamp.io.service.UserService;
import com.jamp.io.utils.servicebeans.UserLoginEvent;

/**
 * Index controller and default user initialized
 * Shows logging form 
 */
@Controller
@RequestMapping(value="/*")
public class RootController {
	@Autowired
	UserService userService;
	
	@Autowired
	SessionData sessionData;
	
	@Autowired
	JmsMessageProducer jmsMessageProducer; 
	
	@RequestMapping
	public String index() {
		if(sessionData.getUser() != null) {
			jmsMessageProducer.sendUserLogin(new UserLoginEvent(LoginEventType.LOGOUT, sessionData.getUser().getName()));
			sessionData.setUser(null);
		}
		
		if(userService.getUserList().size()==0) {
			userService.addMentor(new Mentor("yura", "111"));
		}
		
		return "index";
	}
}
