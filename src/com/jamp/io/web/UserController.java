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
public class UserController {

	@Autowired
	private UserService userDao;

	@RequestMapping(value="/add", method=RequestMethod.POST, params={"name", "pass"})
	public String addUser(@RequestParam String name, @RequestParam String pass, ModelAndView modelAndView) {
		User user = new User();
		user.setName(name);
		user.setPassword(pass);
		userDao.saveUser(user);
		return "redirect:/user";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView addUser(ModelAndView modelAndView) {
		modelAndView.setViewName("user");
		List<User> sd = userDao.getUserList();
		modelAndView.addObject("users", sd);
		return modelAndView;
	}
	
	@RequestMapping(value="/delete", params="id")
	public String deleteUser(@RequestParam long id) {
		userDao.deleteUser(id);
		return "redirect:/user";
	}
}
