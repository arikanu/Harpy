package com.harpy.hag.c;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.harpy.hag.vm.Login;

@Controller
public class HomeC {
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView root() {
		System.out.println("@CTRLR: /GET");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("m", new Login());
		return mav;
	}
	
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		System.out.println("@CTRLR: homeGET");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("m", new Login());
		return mav;
	}
	
}