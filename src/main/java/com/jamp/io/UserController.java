package com.jamp.io;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamp.io.data.User;
import com.jamp.io.data.UsersService;

@RestController
public class UserController {
	
	@Autowired
	UsersService userService;
	
	@RequestMapping("/users/get/all")
	public List<User> getUsers() {
	    return userService.getAll();
	}
}
