package com.harpy.hag.vm.admin.exam;

import java.util.List;

import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.db.entities.exam.ExamMasterType;
import com.harpy.hag.db.entities.exam.ExamSubType;
import com.harpy.hag.vm.VM;

public class Browse extends VM {
	
	private List<ExamMasterType> examMasterTypes = ExamMasterType.populateMasterTypes();
	private int examMasterTypeId;
	private List<ExamSubType> examSubTypes;
	private int examSubTypeId;
	private List<Exam> exams;
	
	public Browse() {
		this.setPageTitle("Browse Exam");
		this.examMasterTypeId = ExamMasterType.getFirstMasterTypeId();
		this.examSubTypes = ExamSubType.populateSubTypes(this.examMasterTypeId);
		this.examSubTypeId = ExamSubType.getFirstSubTypeId(this.examMasterTypeId);
		this.exams = Exam.populateExams(this.examSubTypeId);
	}
	
	public Browse(int examMasterTypeId, int examSubTypeId) {
		this.setPageTitle("Browse Exam");
		this.examMasterTypeId = examMasterTypeId;
		this.examSubTypes = ExamSubType.populateSubTypes(this.examMasterTypeId);
		this.examSubTypeId = examSubTypeId;
		if (subTypeMissingInList(this.examSubTypes, this.examSubTypeId)) {
			this.examSubTypeId = ExamSubType.getFirstSubTypeId(this.examMasterTypeId);
		}
		this.exams = Exam.populateExams(this.examSubTypeId);
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
	public void setExamMasterTypeId(int examMasterTypeId) {
		this.examMasterTypeId = examMasterTypeId;
	}
	public void setExamSubTypeId(int examSubTypeId) {
		this.examSubTypeId = examSubTypeId;
	}
	
}
