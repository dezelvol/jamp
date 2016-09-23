package com.jamp.io.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jamp.io.model.dao.UserDao;
import com.jamp.io.model.pojo.User;
import com.jamp.io.service.UserService;

@Controller
@RequestMapping(value="/user")
@Order(2)
public class UserController {

	@Autowired
	private UserService userDao;
	
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
	
	@RequestMapping(value="/delete", params="id")
	public ModelAndView deleteUser(@RequestParam long id) {

		ModelAndView modelAndView = new ModelAndView("user");
		modelAndView.setViewName("user");
		
		userDao.deleteUser(id);
		List<User> sd = userDao.getUserList();
		modelAndView.addObject("users", sd);
		return modelAndView;
	}
}
