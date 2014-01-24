package com.harpy.hag.db.entities.exam;

import java.util.ArrayList;
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

import org.hibernate.Session;

import com.harpy.hag.utils.HibernateUtil;

@Entity
public class ExamSubType {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="examSubTypeId")
	@TableGenerator(name="examSubTypeId", table="pkexamSubType", pkColumnName="examSubTypeKey", pkColumnValue="examSubTypeValue", allocationSize=1)
	private int examSubTypeId;
	
	@Column(nullable=false)
	private String code;
	
	@Column(nullable=false)
	private String name;
		
	@OneToMany(targetEntity=Exam.class, mappedBy="examSubType", cascade=CascadeType.ALL/*, fetch=FetchType.EAGER*/)
	private List<Exam> exams;
	
	@ManyToOne
	@JoinColumn(name="examMasterTypeId")
	private ExamMasterType examMasterType;

	
	
	public static List<ExamSubType> populateSubTypes(int masterTypeId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String q = "from ExamSubType where examMasterTypeId = " + masterTypeId;
		ArrayList<ExamSubType> examSubTypes = new ArrayList<ExamSubType>(session.createQuery(q).list());
		return examSubTypes;
	}
	public static int getFirstSubTypeId(int masterTypeId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String q = "select min(examSubTypeId) from ExamSubType where examMasterTypeId = " + masterTypeId;
		ArrayList<Integer> minSubTypeId = new ArrayList<Integer>(session.createQuery(q).list());
		if (minSubTypeId.size() > 0) {
			return minSubTypeId.get(0);
		}
		return 0;
	}
	
	
	// Getters & Setters
	public int getExamSubTypeId() {
		return examSubTypeId;
	}
	public void setExamSubTypeId(int examSubTypeId) {
		this.examSubTypeId = examSubTypeId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Exam> getExams() {
		return exams;
	}
	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
	public ExamMasterType getExamMasterType() {
		return examMasterType;
	}
	public void setExamMasterType(ExamMasterType examMasterType) {
		this.examMasterType = examMasterType;
	}
	
	
}
