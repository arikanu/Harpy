package com.harpy.hag.c.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.harpy.hag.vm.admin.Home;


@Controller
@RequestMapping(value = "/admin/*", method = RequestMethod.GET)
public class AdminHomeC {
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView adminHome() {
		System.out.println("@CTRLR: adminHomeGET");
		
		ModelAndView mav = new ModelAndView("admin/home");
		Home vmHome = new Home(SecurityContextHolder.getContext().getAuthentication().getName());
		mav.addObject("m", vmHome);
		return mav;
	}

}
