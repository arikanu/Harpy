package com.harpy.hag.vm.admin.exam;

import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.vm.VM;

public class View extends VM {

	private Exam exam;
	
	public View(int examId) {
		this.setPageTitle("Admin/Exam/View/" + examId);
		this.exam = Exam.examById(examId);
	}

	
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
}
