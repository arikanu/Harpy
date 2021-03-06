package com.harpy.hag.c.student;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Session;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.harpy.hag.db.entities.user_exam.ExamSolution;
import com.harpy.hag.utils.HibernateUtil;
import com.harpy.hag.vm.VM;
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
		mav.addObject("m", new VM("Student/Exam/Home"));
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
	@RequestMapping(value = "/browsed", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute Browse search) {
		System.out.println("@CTRLR: studentExamBrowsedPOST");		
		
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
		Solve solve = new Solve(examId);
		solve.setStartDate(new Date());
		mav.addObject("m", solve);
		
		System.out.println("00: " + solve.getQuestionViewTypes()[0][0]);
		System.out.println("11: " + solve.getQuestionViewTypes()[1][1]);
		
		return mav;
	}
	
	@RequestMapping(value = "/solved", method = RequestMethod.POST)
	public ModelAndView solved(@ModelAttribute Solve solve) {
		System.out.println("@CTRLR: studentExamSolvedPOST");
		
		Date endDate = new Date();
		String hiddenJson = solve.getHiddenJson();
		ArrayList<Integer> answers = solve.getAnswers();
		Solve solved = new Solve(hiddenJson, answers, endDate);
		String emailAddress = SecurityContextHolder.getContext().getAuthentication().getName();		
		Solve.saveExamSolution(solved, emailAddress);
		
		ModelAndView mav = new ModelAndView("student/home");
		return mav;
	}
}
