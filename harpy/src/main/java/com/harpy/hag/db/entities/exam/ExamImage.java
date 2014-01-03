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
public class ExamImage {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="examImageId")
	@TableGenerator(name="examImageId", table="pkExamImage", pkColumnName="examImageKey", pkColumnValue="examImageValue", allocationSize=1)
	private int examImageId;
	
	@Column(nullable=false)
	private byte[] image;
	
	@ManyToOne
	@JoinColumn(name="examId")
	private Exam exam;
	
	
	// Getters & Setters
	public int getExamImageId() {
		return examImageId;
	}
	public void setExamImageId(int examImageId) {
		this.examImageId = examImageId;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
	
}
