package com.harpy.hag.c.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.harpy.hag.authentication.LoginAuthentication;


@Controller
@RequestMapping(value = "/admin/*", method = RequestMethod.GET)
public class AdminHomeC {
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView adminHome() {
		System.out.println("@CTRLR: adminHomeGET");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/home");
		mav.addObject("user", LoginAuthentication.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
		return mav;
	}

}
