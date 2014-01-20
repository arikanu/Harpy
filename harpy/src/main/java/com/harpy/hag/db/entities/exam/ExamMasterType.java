package com.harpy.hag.db.entities.exam;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import org.hibernate.Session;

import com.harpy.hag.db.utils.HibernateUtil;

@Entity
public class ExamMasterType {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="examMasterTypeId")
	@TableGenerator(name="examMasterTypeId", table="pkExamMasterType", pkColumnName="examMasterTypeKey", pkColumnValue="examMasterTypeValue", allocationSize=1)
	private int examMasterTypeId;
	
	@Column(nullable=false)
	private String code;
	
	@Column(nullable=false)
	private String name;	

	@OneToMany(targetEntity=ExamSubType.class, mappedBy="examMasterType", cascade=CascadeType.ALL/*, fetch=FetchType.EAGER*/)
	private List<ExamSubType> examSubTypes;
	
	
	
	
	public static List<ExamMasterType> populateMasterTypes() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String q = "from ExamMasterType";
		ArrayList<ExamMasterType> examMasterTypes = new ArrayList<ExamMasterType>(session.createQuery(q).list());
		return examMasterTypes;
	}
	public static int getFirstMasterTypeId() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String q = "select min(examMasterTypeId) from ExamMasterType";
		ArrayList<Integer> minMasterTypeId = new ArrayList<Integer>(session.createQuery(q).list());
		if (minMasterTypeId.size() > 0) {
			return minMasterTypeId.get(0);
		}
		return 0;
	}
	
	// Getters & Setters
	public int getExamMasterTypeId() {
		return examMasterTypeId;
	}
	public void setExamMasterTypeId(int examMasterTypeId) {
		this.examMasterTypeId = examMasterTypeId;
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
	public List<ExamSubType> getExamSubTypes() {
		return examSubTypes;
	}
	public void setExamSubTypes(List<ExamSubType> examSubTypes) {
		this.examSubTypes = examSubTypes;
	}
}
