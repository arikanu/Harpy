package com.harpy.hag.c.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harpy.hag.vm.ViewModel;
import com.harpy.hag.vm.admin.exam.Browse;
import com.harpy.hag.vm.student.exam.History;
import com.harpy.hag.vm.student.exam.Solve;

@Controller
@RequestMapping(value = "/student/exam/*", method = RequestMethod.GET)
public class StudentExamC {
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		System.out.println("@CTRLR: studentExamHomeGET");
		
		ModelAndView mav = new ModelAndView("student/exam/home");
		mav.addObject("m", new ViewModel("Student/Exam/Home"));
		return mav;
	}	
	
	// BROWSE EXAM
	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	public ModelAndView search() {
		System.out.println("@CTRLR: studentExamBrowseGET");
		
		ModelAndView mav = new ModelAndView("student/exam/browse");
		mav.addObject("m", new Browse());
		return mav;
	}
	@RequestMapping(value = "/browsePost", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute Browse search) {
		System.out.println("@CTRLR: studentExamBrowsePOST");		
		
		ModelAndView mav = new ModelAndView("student/exam/browse");
		mav.addObject("m", new Browse(search.getExamMasterTypeId(), search.getExamSubTypeId()));
		return mav;
	}
	
	// HISTORY
	@RequestMapping(value = "/history/{examId}", method = RequestMethod.GET)
	public ModelAndView history(@PathVariable int examId) {
		System.out.println("@CTRLR: studentExamHistoryGET");
		
		ModelAndView mav = new ModelAndView("student/exam/history");
		mav.addObject("m", new History(examId));
		return mav;
	}
	
	// SOLVE
		@RequestMapping(value = "/solve/{examId}", method = RequestMethod.GET)
		public ModelAndView solve(@PathVariable int examId) {
			System.out.println("@CTRLR: studentExamSolveGET");
			
			ModelAndView mav = new ModelAndView("student/exam/solve");
			mav.addObject("m", new Solve(examId));
			return mav;
		}
}
