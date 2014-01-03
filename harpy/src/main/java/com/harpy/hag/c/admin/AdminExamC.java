package com.harpy.hag.c.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.harpy.hag.vm.admin.exam.Browse;
import com.harpy.hag.vm.admin.exam.Read;


@Controller
@RequestMapping(value = "/admin/exam/*", method = RequestMethod.GET)
public class AdminExamC {
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		System.out.println("@CTRLR: adminExamHomeGET");
		
		ModelAndView mav = new ModelAndView("admin/exam/home");
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
	@RequestMapping(value = "/browsePost", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute Browse search) {
		System.out.println("@CTRLR: adminExamBrowsePOST");		
		
		ModelAndView mav = new ModelAndView("admin/exam/browse");
		mav.addObject("m", new Browse(search.getExamMasterTypeId(), search.getExamSubTypeId()));
		return mav;
	}
	
	
	// UPLOAD EXAM
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView upload() {
		System.out.println("@CTRLR: adminExamUploadGET");
		
		ModelAndView mav = new ModelAndView("admin/exam/upload");
		mav.addObject("m", new Read());
		return mav;
	}
	
	@RequestMapping(value = "/uploaded", method = RequestMethod.POST)
	public ModelAndView uploaded(@ModelAttribute Read read, @RequestParam(required=false , value = "upload") String uploadFlag) {
		System.out.println("@CTRLR: adminExamUploadedPOST");			
		
		if (uploadFlag != null) {
			ModelAndView mav = new ModelAndView("admin/exam/upload");
			boolean uploaded = Read.uploadToDb(read.getExam());
			String uploadedMessage;
			if (uploaded) {
				uploadedMessage = "The exam is uploaded to the DB.";
				mav.addObject("m", new Read(uploadedMessage));
				return mav;
			} else {
				uploadedMessage = "The exam could not be uploaded to the DB!";
				mav.addObject("m", new Read(read.getJsonFile(), uploadedMessage));
				return mav;
			}			
		} else {
			ModelAndView mav = new ModelAndView("admin/exam/upload");
			mav.addObject("m", new Read(read.getJsonFile()));
			return mav;
		}
	}
	
	@RequestMapping(value = "/uploadtodb", method = RequestMethod.POST)
	public ModelAndView uploadToDb(@ModelAttribute Read read, @RequestParam(required=false , value = "upload") String uploadFlag) {
		System.out.println("@CTRLR: adminExamUploadToDbPOST");			
		
		
		
		System.out.println("exam.key = " + read.getExam().getKey());
		
		ModelAndView mav = new ModelAndView("admin/exam/upload");
		mav.addObject("m", new Read(read.getJsonFile()));		
		return mav;
	}
	
}
