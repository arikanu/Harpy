package com.harpy.hag.c;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harpy.hag.authentication.LoginAuthentication;
import com.harpy.hag.authentication.HagSuccessHandler;
import com.harpy.hag.db.entities.user.User;
import com.harpy.hag.vm.Login;

@Controller
public class HomeC {
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView root() {
		System.out.println("@CTRLR: /GET");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("m", new Login("Login"));
		return mav;
	}
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		System.out.println("@CTRLR: homeGET");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("m", new Login("Login"));
		return mav;
	}
	
		
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public void homePost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute User login) throws IOException, ServletException {
		System.out.println("@CTRLR: homePOST");
		
		//PhnxSuccessHandler psh = new PhnxSuccessHandler();
		//Authentication auth = new UsernamePasswordAuthenticationToken(login.getEmailAddress(), login.getPassword());
		//psh.onAuthenticationSuccess(request, response, auth);
	}
	
	/*
	@RequestMapping(value = "/student/home", method = RequestMethod.GET)
	public ModelAndView studentHome() {
		System.out.println("@CTRLR: studentHomeGET");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("student/home");
		mav.addObject("user", LoginAuthentication.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
		return mav;
	}
	*/
}
