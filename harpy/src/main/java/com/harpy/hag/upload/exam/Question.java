package com.harpy.hag.upload.exam;

import java.util.List;

public class Question {

	private String questionHtml;
	private List<String> choicesHtml;
	private List<Integer> choicesLineIndex;
	private String testName;
	private int number;
	private String correctAnswer;
	
	
	// Getters & Setters
	public String getQuestionHtml() {
		return questionHtml;
	}
	public void setQuestionHtml(String questionHtml) {
		this.questionHtml = questionHtml;
	}
	public List<String> getChoicesHtml() {
		return choicesHtml;
	}
	public void setChoicesHtml(List<String> choicesHtml) {
		this.choicesHtml = choicesHtml;
	}
	public List<Integer> getChoicesLineIndex() {
		return choicesLineIndex;
	}
	public void setChoicesLineIndex(List<Integer> choicesLineIndex) {
		this.choicesLineIndex = choicesLineIndex;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	
}
