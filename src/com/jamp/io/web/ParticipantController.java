package com.jamp.io.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jamp.io.model.pojo.Mentor;
import com.jamp.io.model.pojo.Participant;
import com.jamp.io.service.UserService;

@Controller
@RequestMapping(value="/participant")
public class ParticipantController {

	@Autowired
	private UserService service;

	@RequestMapping(value="/add", method=RequestMethod.POST, params={"name", "pass", "mentor"})
	public String addMentor(@RequestParam String name, @RequestParam String pass, @RequestParam long mentor, ModelAndView modelAndView) {
		Participant user = new Participant();
		user.setName(name);
		user.setPassword(pass);
		user.setMentor(service.getMentor(mentor));
		service.addParticipant(user);
		return "redirect:/user";
	}
	
	@RequestMapping(value="/delete", params="id")
	public String deleteMentor(@RequestParam long id) {
		service.deleteMentor(id);
		return "redirect:/user";
	}
}
