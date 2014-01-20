package com.harpy.hag.vm.student.exam;

import com.harpy.hag.db.entities.exam.Choice;

public class ChoiceSoln extends Choice {

	private boolean answered = false;

	public boolean isAnswered() {
		return answered;
	}
	public void setAnswered(boolean answered) {
		this.answered = answered;
	}
}
