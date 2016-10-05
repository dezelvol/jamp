package com.jamp.io.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jamp.io.model.pojo.Mentor;
import com.jamp.io.service.UserService;

@Controller
@RequestMapping(value="/mentor")
public class MentorController {

	@Autowired
	private UserService service;

	@RequestMapping(value="/add", method=RequestMethod.POST, params={"name", "pass"})
	public String addMentor(@RequestParam String name, @RequestParam String pass) {
		Mentor user = new Mentor();
		user.setName(name);
		user.setPassword(pass);
		service.addMentor(user);
		return "redirect:/user";
	}
	
	@RequestMapping(value="/delete", params="id")
	public String deleteMentor(@RequestParam long id) {
		service.deleteMentor(id);
		return "redirect:/user";
	}
}
