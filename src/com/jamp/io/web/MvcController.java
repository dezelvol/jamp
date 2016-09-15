package com.jamp.io.web;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jamp.io.model.User;

@Controller
@RequestMapping(value="/*")
public class MvcController {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	EntityManager entityManager;
	
	@RequestMapping
	public String index() {
		System.out.println("index");
		User user = new User();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.flush();
		return "index";
	}
}
