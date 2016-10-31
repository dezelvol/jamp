package com.jamp.io.web.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jamp.io.jms.JmsMessageProducer;
import com.jamp.io.model.pojo.Participant;
import com.jamp.io.service.UserService;
import com.jamp.io.utils.ParticipantValidator;

/**
 * Simple class to maintain actions related to participant objects
 */
@Controller
@RequestMapping(value="/participant")
public class ParticipantController {

	@Autowired
	private UserService service;
	
	@Autowired
	private ParticipantValidator participantValidator;
	
	@Autowired
	JmsMessageProducer jmsMessageProducer; 
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addMentor(Participant participant, BindingResult results, RedirectAttributes redirectAttributes) {
		participantValidator.validate(participant, results);
		if(results.hasFieldErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.participant", results);
            redirectAttributes.addFlashAttribute("participant", participant);
		} else {
			jmsMessageProducer.sendPageActivity("Adding participant");
			service.addParticipant(participant);	
		}
		return "redirect:/user";
	}
	
	@RequestMapping(value="/delete", params="id")
	public String deleteMentor(@RequestParam long id) {
		service.deleteMentor(id);
		return "redirect:/user";
	}
}
