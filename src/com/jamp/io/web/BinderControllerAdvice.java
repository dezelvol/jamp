package com.jamp.io.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class BinderControllerAdvice {
	@Autowired
	private ConversionService conversionService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
//		binder.setConversionService(conversionService);
	}
}
