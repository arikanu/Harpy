package com.harpy.hag.vm.student.exam;

import com.harpy.hag.vm.ViewModel;


public class Solve extends ViewModel {

	private ExamSoln sExam;
	
	public Solve(int examId) {
		super("Student/Exam/Solve/" + examId);		
		this.sExam = new ExamSoln(examId);
		
	}

	public ExamSoln getsExam() {
		return sExam;
	}
	public void setsExam(ExamSoln sExam) {
		this.sExam = sExam;
	}

}
