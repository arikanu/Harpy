package com.harpy.hag.vm.student.exam;

import java.util.ArrayList;
import java.util.List;

import com.harpy.hag.db.entities.exam.Choice;
import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.db.entities.exam.Question;
import com.harpy.hag.db.entities.exam.Test;

public class ExamSoln extends Exam {
	
	private List<TestSoln> sTests = new ArrayList<TestSoln>();

	
	public ExamSoln(int examId) {
		Exam oriExam = Exam.examById(examId);
		this.setKey(oriExam.getKey());
		for (Test test : oriExam.getTests()) {
			TestSoln sTest = new TestSoln();			
			sTest.setTestId(test.getTestId());
			sTest.setName(test.getName());
			
			List<QuestionSoln> sQuestions = new ArrayList<QuestionSoln>();
			for (Question question : test.getQuestions()) {
				QuestionSoln sQuestion = new QuestionSoln();
				sQuestion.setQuestionId(question.getQuestionId());
				sQuestion.setNumber(question.getNumber());
				sQuestion.setQuestionHtml(question.getQuestionHtml());
				sQuestion.setCorrectAnswer(question.getCorrectAnswer());
				
				List<ChoiceSoln> sChoices = new ArrayList<ChoiceSoln>();
				for (Choice choice : question.getChoices()) {
					ChoiceSoln sChoice = new ChoiceSoln();
					sChoice.setChoiceCode(choice.getChoiceCode());
					sChoice.setChoiceHtml(choice.getChoiceHtml());
					sChoice.setCorrectAnswer(choice.isCorrectAnswer());
					
					sChoices.add(sChoice);
				}
				sQuestion.setsChoices(sChoices);
				sQuestions.add(sQuestion);
			}
			sTest.setsQuestions(sQuestions);
			this.sTests.add(sTest);
		}
		
	}
	
	
	public List<TestSoln> getsTests() {
		return sTests;
	}
	public void setsTests(List<TestSoln> sTests) {
		this.sTests = sTests;
	}
}
