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
public class Test {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="testId")
	@TableGenerator(name="testId", table="pkTest", pkColumnName="testKey", pkColumnValue="testValue", allocationSize=1)
	private int testId;
	
	@Column(nullable=false)
	private int number;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private int nbOfQuestions;
	
	@OneToMany(targetEntity=Question.class, mappedBy="test", cascade=CascadeType.ALL/*, fetch=FetchType.EAGER*/)
	private List<Question> questions;
	
	@ManyToOne
	@JoinColumn(name="examId")
	private Exam exam;

	
	
	
	// Getters & Setters
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNbOfQuestions() {
		return nbOfQuestions;
	}
	public void setNbOfQuestions(int nbOfQuestions) {
		this.nbOfQuestions = nbOfQuestions;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
}
