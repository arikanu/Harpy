package com.harpy.hag.db.entities.user_exam;

import java.util.ArrayList;
import java.util.Date;
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

import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.db.entities.exam.Test;
import com.harpy.hag.db.entities.user.User;

@Entity
public class ExamSolution {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="examSolutionId")
	@TableGenerator(name="examSolutionId", table="pkExamSolution", pkColumnName="examSolutionKey", pkColumnValue="examSolutionIdValue", allocationSize=1)
	private int examSolutionId;
	
	@ManyToOne
	@JoinColumn(name="examId")
	private Exam exam;
	
	@ManyToOne
	@JoinColumn(name="emailAddress")
	private User user;
	
	@Column(nullable=true)
	private Date startDate;
	
	@Column(nullable=true)
	private Date endDate;

	@OneToMany(targetEntity=QuestionSolution.class, mappedBy="examSolution", cascade=CascadeType.ALL/*, fetch=FetchType.EAGER*/)
	private List<QuestionSolution> questionSolutions;
	
	
	public ExamSolution() { }
	public ExamSolution(Exam exam, User user, Date startDate, Date endDate) {
		this.exam = exam;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	
	
	public int getExamSolutionId() {
		return examSolutionId;
	}
	public Exam getExam() {
		return exam;
	}
	public User getUser() {
		return user;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setExamSolutionId(int examSolutionId) {
		this.examSolutionId = examSolutionId;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
}
