package com.jamp.io.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jamp.io.enums.LoginEventType;
import com.jamp.io.jms.JmsMessageProducer;
import com.jamp.io.model.pojo.SessionData;
import com.jamp.io.model.pojo.User;
import com.jamp.io.service.UserService;
import com.jamp.io.utils.servicebeans.UserLoginEvent;


@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	UserService userService;
	
	@Autowired
	SessionData sessionData;

	@Autowired
	JmsMessageProducer jmsMessageProducer; 
	
	@RequestMapping(method=RequestMethod.POST, params={"name", "pass"})
	public String index(@RequestParam String name, @RequestParam String pass) {
		User user = userService.getUser(name);
		if(user!=null && user.getPassword().equals(pass)) {
			jmsMessageProducer.sendUserLogin(new UserLoginEvent(LoginEventType.LOGIN, user.getName()));
			sessionData.setUser(user);
			return "redirect:user";	
		}
		
		jmsMessageProducer.sendUserLogin(new UserLoginEvent(LoginEventType.LOGIN_FAILED, null));
		return "loginFailed";
	}
}
