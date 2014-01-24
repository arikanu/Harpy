package com.harpy.hag.vm.admin.exam;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.web.multipart.MultipartFile;

import com.harpy.hag.db.entities.exam.Choice;
import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.db.entities.exam.ExamImage;
import com.harpy.hag.db.entities.exam.ExamSubType;
import com.harpy.hag.db.entities.exam.Question;
import com.harpy.hag.db.entities.exam.Test;
import com.harpy.hag.utils.HibernateUtil;
import com.harpy.hag.utils.JsonUtil;
import com.harpy.hag.utils.QueryUtil;
import com.harpy.hag.vm.VMHidden;
import com.harpy.hag.vm.ImpVMHidden;

public class Upload extends VMHidden implements ImpVMHidden {
	
	private MultipartFile jsonFile;
	private String jsonFileName;
	private boolean existsInDb;
	private Exam exam;
	private String examSubTypeCode;
	private String uploadedMessage;
	

	// Empty ViewModel
	public Upload() {
		this.setPageTitle("Upload Exam");
	}
	
	// Created when jsonFile is submitted
	public Upload(MultipartFile jsonfile) {
		this.setPageTitle("Upload Exam");
		com.harpy.hag.upload.exam.Exam jExam = (com.harpy.hag.upload.exam.Exam) JsonUtil.jsonfileToObj(jsonfile, com.harpy.hag.upload.exam.Exam.class);
		if (jExam != null) {
			this.examSubTypeCode = jExam.getSubType();
			this.uploadedMessage = "Json file (Exam) is read.";
		}
		this.exam = new Exam(jExam);
		this.existsInDb = dbIsExistsInDb();
		packHiddenObjects();
	}
	
	// Created when read jsonFile is tried to be uploaded to DB
	public Upload(String hiddenJson) {
		this.setPageTitle("Upload Exam");
		extractHiddenObjects(hiddenJson);
		boolean uploaded = Upload.uploadToDb(this.exam, this.examSubTypeCode);
		if (uploaded) {
			this.uploadedMessage = "Exam is successfully uploaded to DB.";
		} else {
			this.uploadedMessage = "Exam could not be uploaded to DB.";
		}
		this.existsInDb = dbIsExistsInDb();
		packHiddenObjects();
	}
	
	
	@Override
	public void packHiddenObjects() {
		ArrayList<Object> hiddenObjects = new ArrayList<Object>();
		hiddenObjects.add(this.exam);
		hiddenObjects.add(this.examSubTypeCode);
		String hiddenJson = VMHidden.generateHiddenJson(hiddenObjects);
		this.setHiddenJson(hiddenJson);
	}

	@Override
	public void extractHiddenObjects(String hiddenJson) {		
		String[] jsons = hiddenJson.split(VMHidden.delimiter);
		String jExam = jsons[0];		
		String jExamSubTypeCode = jsons[1];
		this.exam = (Exam) JsonUtil.jsonToObj(jExam, Exam.class);
		this.examSubTypeCode = (String) JsonUtil.jsonToObj(jExamSubTypeCode, String.class);
	}
	
	
	
	
	public MultipartFile getJsonFile() {
		return jsonFile;
	}
	public String getJsonFileName() {
		return jsonFileName;
	}
	public boolean isExistsInDb() {
		return existsInDb;
	}
	public Exam getExam() {
		return exam;
	}
	public String getUploadedMessage() {
		return uploadedMessage;
	}
	public String getExamSubTypeCode() {
		return examSubTypeCode;
	}
	public void setJsonFile(MultipartFile jsonFile) {
		this.jsonFile = jsonFile;
	}
	
	
	public boolean dbIsExistsInDb() {
		if (exam == null) { return false; }
		else {
			String query = "from Exam where key = '" + exam.getKey() + "'";
			List<Exam> exams = QueryUtil.select(Exam.class, query);
			if (exams.size() == 0) { return false; }
			return true;
		}
	}
	public static boolean uploadToDb (Exam exam, String examSubTypeCode) {
		if (exam == null) { return false; }
		else {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String q = "from ExamSubType where code = '" + examSubTypeCode + "'";
			ArrayList<ExamSubType> examSubTypes = new ArrayList<ExamSubType>(session.createQuery(q).list());
			if (examSubTypes != null && examSubTypes.size() > 0) {
				exam.setExamSubType(examSubTypes.get(0));
			} else { 
				System.out.println("Subtype could not be found while uploading exam!"); 
				return false;
			}
			try {
				for (Test test : exam.getTests()) {
					test.setExam(exam);
					for (Question question : test.getQuestions()) {
						question.setTest(test);
						for (Choice choice : question.getChoices()) {
							choice.setQuestion(question);
						}
					}
					for (ExamImage examImage : exam.getImages()) {
						examImage.setExam(exam);
					}
				}
				session.save(exam);
				session.getTransaction().commit();
				return true;
			} catch (Exception e) {
				System.out.println("Error while uploading exam!"); 
				return false;
			}
		}		
	}

	
}
