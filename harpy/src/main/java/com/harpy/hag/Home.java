package com.harpy.hag;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Home {

	@RequestMapping("/hello")
	public ModelAndView hello() {
		System.out.println("HELLODAYIM");
		return new ModelAndView("hello", "message", "Body Content goes Here !");
	}
}
