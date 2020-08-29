package com.mytestapp;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class testController {

	@RequestMapping("/")
	public ModelAndView welcome() {
		ModelAndView model = new ModelAndView("index");
		
			return model;
	}
	
	@RequestMapping(value="/admin/private")
	public ModelAndView privatePage(Principal principal) {
		ModelAndView model = new ModelAndView("welcome");
		model.addObject("name", principal.getName());
		return model;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView("login");
		return model;
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public ModelAndView loginFailed() {
		ModelAndView model = new ModelAndView("login");
		model.addObject("error", "true");
		return model;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView loginOut() {
		ModelAndView model = new ModelAndView("login");
		
		return model;
	}
}
