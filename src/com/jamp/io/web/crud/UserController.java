package com.jamp.io.web.crud;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jamp.io.model.pojo.Mentor;
import com.jamp.io.model.pojo.Participant;
import com.jamp.io.model.pojo.User;
import com.jamp.io.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userDao;
	
	@RequestMapping(method=RequestMethod.GET)
	public String addUser(Model modelAndView) {
		
		List<User> sd = userDao.getUserList();
		modelAndView.addAttribute("users", sd);
		List<Mentor> ml = userDao.getMentorList();
		modelAndView.addAttribute("mentors", ml);
		List<Participant> pl = userDao.getParticipantList();
		modelAndView.addAttribute("participants", pl);
		if(!modelAndView.containsAttribute("mentor"))modelAndView.addAttribute("mentor", new Mentor());
		if(!modelAndView.containsAttribute("participant"))modelAndView.addAttribute("participant", new Participant());
		return "user";
	}
	
	@RequestMapping(value="/delete", params="id")
	public String deleteUser(@RequestParam long id) {
		userDao.deleteUser(id);
		return "redirect:/user";
	}
}
