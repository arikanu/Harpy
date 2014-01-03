package com.harpy.hag.db.entities.exam;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="questionId")
	@TableGenerator(name="questionId", table="pkQuestion", pkColumnName="questionKey", pkColumnValue="questionValue", allocationSize=1)
	private int questionId;
	
	@Column(nullable=false)
	private String questionHtml;
	
	@Column(nullable=false)
	private int number;
	
	@Column(nullable=false)
	private String correctAnswer;
	
	@ManyToOne
	@JoinColumn(name="testId")
	private Test test;

	@OneToMany(targetEntity=Choice.class, mappedBy="question", cascade=CascadeType.ALL/*, fetch=FetchType.EAGER*/)
	@Column(nullable=false)
	private List<Choice> choices;
	
	
	
	// Getters & Setters
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionHtml() {
		return questionHtml;
	}
	public void setQuestionHtml(String questionHtml) {
		this.questionHtml = questionHtml;
	}
	public List<Choice> getChoices() {
		return choices;
	}
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
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
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	
}
