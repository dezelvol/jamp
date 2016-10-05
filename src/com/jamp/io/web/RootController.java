package com.jamp.io.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jamp.io.model.pojo.Mentor;
import com.jamp.io.service.UserService;


@Controller
@RequestMapping(value="/*")
public class RootController {
	@Autowired
	UserService userService;
	@RequestMapping
	public String index() {
		if(userService.getUserList().size()==0) {
			userService.addMentor(new Mentor("yura", "111"));
		}
		
		return "index";
	}
}
