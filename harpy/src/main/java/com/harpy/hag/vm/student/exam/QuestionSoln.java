package com.harpy.hag.vm.student.exam;

import java.util.List;
import com.harpy.hag.db.entities.exam.Question;

public class QuestionSoln extends Question {
	private boolean answered = false;
	private boolean correct = false;
	private List<ChoiceSoln> sChoices;
	
	public boolean isAnswered() {
		return answered;
	}
	public boolean isCorrect() {
		return correct;
	}
	public List<ChoiceSoln> getsChoices() {
		return sChoices;
	}
	public void setAnswered(boolean answered) {
		this.answered = answered;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public void setsChoices(List<ChoiceSoln> sChoices) {
		this.sChoices = sChoices;
	}	

}
