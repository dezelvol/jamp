package com.jamp.io.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/*")
public class RootController {
	
	@RequestMapping
	public String index() {
		return "index";
	}
}
