package com.harpy.hag.c.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harpy.hag.vm.VM;
import com.harpy.hag.vm.admin.exam.Browse;
import com.harpy.hag.vm.admin.exam.Upload;
import com.harpy.hag.vm.admin.exam.View;


@Controller
@RequestMapping(value = "/admin/exam/*", method = RequestMethod.GET)
public class AdminExamC {
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		System.out.println("@CTRLR: adminExamHomeGET");
		
		ModelAndView mav = new ModelAndView("admin/exam/home");
		mav.addObject("m", new VM("Admin/Exam/Home"));
		return mav; 
	}
	
	
	// BROWSE EXAM
	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	public ModelAndView search() {
		System.out.println("@CTRLR: adminExamBrowseGET");
		
		ModelAndView mav = new ModelAndView("admin/exam/browse");
		mav.addObject("m", new Browse());
		return mav;
	}
	@RequestMapping(value = "/browsed", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute Browse search) {
		System.out.println("@CTRLR: adminExamBrowsedPOST");		
		
		ModelAndView mav = new ModelAndView("admin/exam/browse");
		mav.addObject("m", new Browse(search.getExamMasterTypeId(), search.getExamSubTypeId()));
		return mav;
	}
	
	// VIEW EXAM
	@RequestMapping(value = "/view/{examId}", method = RequestMethod.GET)
	public ModelAndView view(@PathVariable int examId){
		System.out.println("@CTRLR: adminExamViewGET");		
		
		ModelAndView mav = new ModelAndView("admin/exam/view");
		mav.addObject("m", new View(examId));
		return mav;
	}
	
	
	// READ & UPLOAD EXAM
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView upload() {
		System.out.println("@CTRLR: adminExamUploadGET");
		
		ModelAndView mav = new ModelAndView("admin/exam/upload");
		mav.addObject("m", new Upload());
		return mav;
	}
	
	@RequestMapping(value = "/uploaded", method = RequestMethod.POST)
	public ModelAndView uploaded(@ModelAttribute("m") Upload m) {
		System.out.println("@CTRLR: adminExamUploadedPOST");
			
		ModelAndView mav = new ModelAndView("admin/exam/upload");
		mav.addObject("m", new Upload(m.getJsonFile()));
		return mav;
	}
	
	@RequestMapping(value = "/uploadedDb", method = RequestMethod.POST)
	public ModelAndView uploadedDb(@ModelAttribute("m") Upload m) {
		System.out.println("@CTRLR: adminExamUploadedDbPOST");
		
		ModelAndView mav = new ModelAndView("admin/exam/upload");
		mav.addObject("m", new Upload(m.getHiddenJson()));		
		return mav;
	}
	
}
