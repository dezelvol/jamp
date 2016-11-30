package com.jamp.io;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexCotroller {
	
	@RequestMapping("/")
	String home() {
		return "home";
	}
}
