package com.harpy.hag.db.entities.user_exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

import com.harpy.hag.db.entities.exam.Question;

@Entity
public class QuestionSolution {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="questionSolutionId")
	@TableGenerator(name="questionSolutionId", table="pkQuestionSolution", pkColumnName="questionSolutionKey", pkColumnValue="questionSolutionIdValue", allocationSize=1)
	private int questionSolutionId;
	
	@ManyToOne
	@JoinColumn(name="questionId")
	private Question question;
	
	@ManyToOne
	@JoinColumn(name="examSolutionId")
	private ExamSolution examSolution;
	
	@Column(nullable=false)
	private int choiceIndex;
	// -1=empty  0=A  1=B  2=C  3=D  4=E

	
	public QuestionSolution() { }
	public QuestionSolution(Question question, ExamSolution examSolution, int choiceIndex) {
		this.question = question;
		this.examSolution = examSolution;
		this.choiceIndex = choiceIndex;
	}
	
	
	
	public int getQuestionSolutionId() {
		return questionSolutionId;
	}
	public Question getQuestion() {
		return question;
	}
	public ExamSolution getExamSolution() {
		return examSolution;
	}
	public int getChoiceIndex() {
		return choiceIndex;
	}
	public void setQuestionSolutionId(int questionSolutionId) {
		this.questionSolutionId = questionSolutionId;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public void setExamSolution(ExamSolution examSolution) {
		this.examSolution = examSolution;
	}
	public void setChoiceIndex(int choiceIndex) {
		this.choiceIndex = choiceIndex;
	}
	
	
	
	
	
}
