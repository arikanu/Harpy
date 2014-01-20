package com.harpy.hag.vm.admin.exam;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.springframework.web.multipart.MultipartFile;

import com.harpy.hag.db.entities.exam.Choice;
import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.db.entities.exam.ExamImage;
import com.harpy.hag.db.entities.exam.ExamSubType;
import com.harpy.hag.db.entities.exam.Question;
import com.harpy.hag.db.entities.exam.Test;
import com.harpy.hag.db.utils.HibernateUtil;
import com.harpy.hag.vm.ViewModel;

public class Read extends ViewModel {

	private MultipartFile jsonFile;	
	private boolean existsInDb;
	private Exam exam;
	private String examJson;
	private String uploadedMessage;
	private String examSubTypeCode;
	
	// Constructors
	public Read() {
		super("Admin/Exam/Upload");
		this.jsonFile = null;
		this.exam = null;
		this.uploadedMessage = "";
	}
	public Read(String uploadedMessage) {
		super("Admin/Exam/Upload");
		this.jsonFile = null;
		this.exam = null;
		this.uploadedMessage = uploadedMessage;
	}	
	public Read(MultipartFile jsonFile, String uploadedMessage) throws JsonGenerationException, JsonMappingException, IOException {
		super("Admin/Exam/Upload");
		this.uploadedMessage = uploadedMessage;
		ObjectMapper mapper = new ObjectMapper();
		com.harpy.hag.upload.exam.Exam jExam;
		try { 
			jExam = mapper.readValue(jsonFile.getBytes(), com.harpy.hag.upload.exam.Exam.class);
			this.examSubTypeCode = jExam.getSubType(); }
		catch (JsonParseException e) { jExam = null; } catch (JsonMappingException e) { jExam = null; }	catch (IOException e) { jExam = null; }			
		this.exam = new Exam(jExam);
		if (this.exam != null) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String q = "from Exam where key = '" + this.exam.getKey() + "'";
			ArrayList<Exam> exams = new ArrayList<Exam>(session.createQuery(q).list());
			this.existsInDb = true;
			if (exams.size() == 0) { this.existsInDb = false; }
			this.examJson = Exam.jsonFromExam(this.exam);			
		}
	}
	public Read(String examJson, String uploadedMessage) throws JsonParseException, JsonMappingException, IOException {
		super("Admin/Exam/Upload");
		this.uploadedMessage = uploadedMessage;
		this.examJson = examJson;
		this.exam = Exam.examFromJson(examJson);
	}
	
	
	// Upload to DB
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
	
	// Getters & Setters
	public MultipartFile getJsonFile() {
		return jsonFile;
	}
	public boolean isExistsInDb() {
		return existsInDb;
	}
	public Exam getExam() {
		return exam;
	}
	public String getExamJson() {
		return examJson;
	}
	public String getUploadedMessage() {
		return uploadedMessage;
	}
	public void setJsonFile(MultipartFile jsonFile) {
		this.jsonFile = jsonFile;
	}
	public void setExistsInDb(boolean existsInDb) {
		this.existsInDb = existsInDb;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	public void setExamJson(String examJson) {
		this.examJson = examJson;
	}
	public void setUploadedMessage(String uploadedMessage) {
		this.uploadedMessage = uploadedMessage;
	}
	public String getExamSubTypeCode() {
		return examSubTypeCode;
	}
	public void setExamSubTypeCode(String examSubTypeId) {
		this.examSubTypeCode = examSubTypeId;
	}
		
}
