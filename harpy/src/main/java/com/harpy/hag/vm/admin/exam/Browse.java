package com.harpy.hag.vm.admin.exam;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.db.entities.exam.ExamMasterType;
import com.harpy.hag.db.entities.exam.ExamSubType;
import com.harpy.hag.db.utils.HibernateUtil;

public class Browse {
	
	private List<ExamMasterType> examMasterTypes = populateMasterTypes();
	private int examMasterTypeId;
	private List<ExamSubType> examSubTypes;
	private int examSubTypeId;
	private List<Exam> exams;	// fill exams!!!
	
	public Browse() {
		this.examMasterTypeId = 1;
		this.examSubTypes = populateSubTypes(this.examMasterTypeId);
		this.examSubTypeId = getFirstSubTypeId(this.examSubTypes);
		this.exams = populateExams(this.examSubTypeId);
	}
	public Browse(int examMasterTypeId, int examSubTypeId) {
		this.examMasterTypeId = examMasterTypeId;
		this.examSubTypes = populateSubTypes(this.examMasterTypeId);
		this.examSubTypeId = examSubTypeId;
		if (subTypeMissingInList(this.examSubTypes, this.examSubTypeId)) {
			this.examSubTypeId = getFirstSubTypeId(this.examSubTypes);
		}
		this.exams = populateExams(this.examSubTypeId);
	}
	
	
	private int getFirstSubTypeId(List<ExamSubType> examSubTypes) {
		int subTypeId = 10000;
		for (ExamSubType subType : examSubTypes) {
			if (subType.getExamSubTypeId() < subTypeId) {
				subTypeId = subType.getExamSubTypeId();
			}
		}
		return subTypeId;
	}
	private boolean subTypeMissingInList(List<ExamSubType> examSubTypes, int examSubTypeId) {
		boolean missing = true;
		for (ExamSubType subType : examSubTypes) {
			if (subType.getExamSubTypeId() == examSubTypeId) {
				missing = false;
			}
		}
		return missing;
	}
	
	private List<ExamMasterType> populateMasterTypes() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String q = "from ExamMasterType";
		ArrayList<ExamMasterType> examMasterTypes = new ArrayList<ExamMasterType>(session.createQuery(q).list());
		return examMasterTypes;
	}
	private List<ExamSubType> populateSubTypes(int masterTypeId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String q = "from ExamSubType where examMasterTypeId = " + masterTypeId;
		ArrayList<ExamSubType> examSubTypes = new ArrayList<ExamSubType>(session.createQuery(q).list());
		return examSubTypes;
	}
	private List<Exam> populateExams(int subTypeId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String q = "from Exam where examSubTypeId = " + subTypeId;
		ArrayList<Exam> exams = new ArrayList<Exam>(session.createQuery(q).list());
		return exams;
	}
	
	
	public List<ExamMasterType> getExamMasterTypes() {
		return examMasterTypes;
	}
	public int getExamMasterTypeId() {
		return examMasterTypeId;
	}
	public List<ExamSubType> getExamSubTypes() {
		return examSubTypes;
	}
	public int getExamSubTypeId() {
		return examSubTypeId;
	}
	public List<Exam> getExams() {
		return exams;
	}
	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
	public void setExamMasterTypes(List<ExamMasterType> examMasterTypes) {
		this.examMasterTypes = examMasterTypes;
	}
	public void setExamMasterTypeId(int examMasterTypeId) {
		this.examMasterTypeId = examMasterTypeId;
	}
	public void setExamSubTypes(List<ExamSubType> examSubTypes) {
		this.examSubTypes = examSubTypes;
	}
	public void setExamSubTypeId(int examSubTypeId) {
		this.examSubTypeId = examSubTypeId;
	}
		
}
