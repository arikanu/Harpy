package com.harpy.hag.db.entities.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity
public class Choice {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="choiceId")
	@TableGenerator(name="choiceId", table="pkChoice", pkColumnName="choiceKey", pkColumnValue="choiceValue", allocationSize=1)
	private int choiceId;
	
	@Column(nullable=false)
	private String choiceHtml;
	
	@Column(nullable=false)
	private String choiceCode;
	
	@Column(nullable=false)
	private int choiceLineIndex;
	
	@Column(nullable=false)
	private boolean correctAnswer;
	
	@ManyToOne
	@JoinColumn(name="questionId")
	private Question question;

	
	
	// Getters & Setters
	public int getChoiceId() {
		return choiceId;
	}
	public void setChoiceId(int choiceId) {
		this.choiceId = choiceId;
	}
	public String getChoiceHtml() {
		return choiceHtml;
	}
	public void setChoiceHtml(String choiceHtml) {
		this.choiceHtml = choiceHtml;
	}
	public String getChoiceCode() {
		return choiceCode;
	}
	public void setChoiceCode(String choiceCode) {
		this.choiceCode = choiceCode;
	}
	public int getChoiceLineIndex() {
		return choiceLineIndex;
	}
	public void setChoiceLineIndex(int choiceLineIndex) {
		this.choiceLineIndex = choiceLineIndex;
	}
	public boolean isCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
}
