package com.harpy.hag.vm.student.exam;

import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.vm.ViewModel;

public class History extends ViewModel {

	private Exam exam;
	
	public History(int examId) {
		super("Student/Exam/History/" + examId);
		this.exam = Exam.examById(examId);
	}

	public Exam getExam() {
		return exam;
	}
	
}
