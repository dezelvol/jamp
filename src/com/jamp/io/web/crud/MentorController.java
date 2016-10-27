package com.jamp.io.web.crud;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jamp.io.jms.JmsMessageProducer;
import com.jamp.io.model.pojo.Mentor;
import com.jamp.io.service.UserService;

@Controller
@RequestMapping(value="/mentor")
public class MentorController {

	@Autowired
	private UserService service;
	
	@Autowired
	JmsMessageProducer jmsMessageProducer; 

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addMentor(@Valid Mentor mentor, BindingResult results, RedirectAttributes redirectAttributes) {
		if(results.hasFieldErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.mentor", results);
            redirectAttributes.addFlashAttribute("mentor", mentor);
		} else {
			jmsMessageProducer.sendPageActivity("Adding mentor");
			service.addMentor(mentor);	
		}
		return "redirect:/user";
	}
	
	@RequestMapping(value="/delete", params="id")
	public String deleteMentor(@RequestParam long id) {
		service.deleteMentor(id);
		return "redirect:/user";
	}
}
