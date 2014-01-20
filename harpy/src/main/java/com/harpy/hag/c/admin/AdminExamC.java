package com.harpy.hag.c.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.vm.ViewModel;
import com.harpy.hag.vm.admin.exam.Browse;
import com.harpy.hag.vm.admin.exam.Read;
import com.harpy.hag.vm.admin.exam.View;


@Controller
@RequestMapping(value = "/admin/exam/*", method = RequestMethod.GET)
public class AdminExamC {
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		System.out.println("@CTRLR: adminExamHomeGET");
		
		ModelAndView mav = new ModelAndView("admin/exam/home");
		mav.addObject("m", new ViewModel("Admin/Exam/Home"));
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
		mav.addObject("m", new Read());
		return mav;
	}
	
	@RequestMapping(value = "/uploaded", method = RequestMethod.POST)
	public ModelAndView uploaded(@ModelAttribute Read read, @RequestParam(required=false , value = "upload") String uploadFlag) throws IOException {					
		System.out.println("@CTRLR: adminExamUploadedPOST");
		
		ModelAndView mav = new ModelAndView("admin/exam/upload");
		if (uploadFlag == null) {			
			mav.addObject("m", new Read(read.getJsonFile(), ""));
			
			Read newRead = new Read(read.getJsonFile(), "");
			Exam newExam = newRead.getExam();
			String newExamJson = Exam.jsonFromExam(newExam);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File("c:\\users\\uarikan\\desktop\\abc.json"), newExam);
			
			return mav;
		} else /*if (uploadFlag != null)*/ {
			Exam exam = Exam.examFromJson(read.getExamJson());
			boolean uploaded = Read.uploadToDb(exam, read.getExamSubTypeCode());
			if (uploaded) {
				mav.addObject("m", new Read("Exam is successfully uploaded to DB."));
			} else {
				mav.addObject("m", new Read(read.getExamJson(), "Exam could not be uploaded to DB!"));
			}
			return mav;
		}				
	}
	
}
