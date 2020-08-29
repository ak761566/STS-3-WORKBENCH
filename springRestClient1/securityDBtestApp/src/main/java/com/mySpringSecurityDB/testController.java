package com.mySpringSecurityDB;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class testController {

	@RequestMapping(value= "/")
	public ModelAndView defaultPage() {
		ModelAndView model = new ModelAndView("hello");
		 model.addObject("title", "Spring Security Login Form - Database Authentication");
		 model.addObject("message", "Home Page - Access to All!");

		return model;
	}
		
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView("login");
		return model;
	}
	
	@RequestMapping(value="/admin/welcome", method = RequestMethod.GET)
	public ModelAndView adminPage(Principal principal) {
	ModelAndView model = new ModelAndView();
	
	String loggedUserName = principal.getName(); 
	
	model.addObject("user", loggedUserName);
	model.addObject("title", "Spring Security Login Form - Database Authentication");
	model.addObject("message", "This page is for ROLE_ADMIN only!");
	model.setViewName("admin");
	return model;

	}

	@RequestMapping(value="/loginError", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView("403");
		model.addObject("error", "true");
		return model;
	}

	
	@RequestMapping(value="/logoutSuccess", method = RequestMethod.GET)
	public ModelAndView logOut() {
		ModelAndView model = new ModelAndView("logout");
		
		return model;
		
	}

	
}
