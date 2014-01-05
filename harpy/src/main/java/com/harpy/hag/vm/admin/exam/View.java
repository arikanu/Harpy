package com.harpy.hag.vm.admin.exam;

import com.harpy.hag.db.entities.exam.Exam;

public class View {

	private Exam exam;
	
	public View(int examId) {
		this.exam = Exam.examById(examId);
	}

	
	public Exam getExam() {
		return exam;
	}	
	
}
