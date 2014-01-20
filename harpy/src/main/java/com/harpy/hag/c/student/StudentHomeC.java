package com.harpy.hag.c.student;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.harpy.hag.vm.student.Home;

@Controller
@RequestMapping(value = "/student/*", method = RequestMethod.GET)
public class StudentHomeC {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView studentHome() {
		System.out.println("@CTRLR: studentHomeGET");
		
		ModelAndView mav = new ModelAndView("student/home");
		Home vmHome = new Home(SecurityContextHolder.getContext().getAuthentication().getName());
		mav.addObject("m", vmHome);
		return mav;
	}
}