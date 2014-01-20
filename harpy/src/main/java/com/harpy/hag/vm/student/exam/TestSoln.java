package com.harpy.hag.vm.student.exam;

import java.util.List;

import com.harpy.hag.db.entities.exam.Test;

public class TestSoln extends Test {
	private List<QuestionSoln> sQuestions;

	public List<QuestionSoln> getsQuestions() {
		return sQuestions;
	}

	public void setsQuestions(List<QuestionSoln> sQuestions) {
		this.sQuestions = sQuestions;
	}

}
