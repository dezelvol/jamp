package com.jamp.io.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jamp.io.model.pojo.User;
import com.jamp.io.service.UserService;


@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	UserService userService;
	
	@Autowired
	User registeredUser;
	
	@RequestMapping(method=RequestMethod.POST, params={"name", "pass"})
	public String index(@RequestParam String name, @RequestParam String pass) {
		User user = userService.getUser(name);
		if(user!=null && user.getPassword().equals(pass)) {
			registeredUser.setName(user.getName());;
			return "redirect:user";	
		}
		
		return "loginFailed";
	}
}
