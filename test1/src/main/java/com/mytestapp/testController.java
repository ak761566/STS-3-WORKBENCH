package com.mytestapp;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class testController {

	/*
	 * @RequestMapping(value= {"/","/welcome**"}, method = RequestMethod.GET) public
	 * ModelAndView welcome(Principal principal) { ModelAndView model = new
	 * ModelAndView("welcome"); model.addObject("name", principal.getName());
	 * 
	 * return model; }
	 */
	
	@RequestMapping("/")
	public ModelAndView welcome() {
		ModelAndView model = new ModelAndView("index");
		return model;
		
	}

	@RequestMapping("/admin/privatePage")
	public ModelAndView privatePage(Principal principal) {
		ModelAndView model = new ModelAndView("welcome");
		return model;
		
	}
}
