package com.harpy.hag.db.entities.exam;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

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
