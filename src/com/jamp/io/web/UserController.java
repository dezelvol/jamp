package com.jamp.io.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jamp.io.model.User;
import com.jamp.io.model.UserDao;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(method=RequestMethod.POST, params="name")
	public ModelAndView addUser(@RequestParam String name, ModelAndView modelAndView) {
		modelAndView.setViewName("user");
		User user = new User();
		user.setName(name);
		userDao.saveUser(user);
		List<User> sd = userDao.getUserList();
		modelAndView.addObject("users", sd);
		return modelAndView;
	}
	
	@RequestMapping(value="/delete", params="name")
	public ModelAndView deleteUser(@RequestParam String name, ModelAndView modelAndView) {
		modelAndView.setViewName("index");
		userDao.deleteUser(name);
		List<User> sd = userDao.getUserList();
		modelAndView.addObject("users", sd);
		return modelAndView;
	}
}
