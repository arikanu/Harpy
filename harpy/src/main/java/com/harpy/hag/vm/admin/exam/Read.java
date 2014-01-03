package com.harpy.hag.vm.admin.exam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.springframework.web.multipart.MultipartFile;
import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.db.utils.HibernateUtil;

public class Read {

	private MultipartFile jsonFile;
	private boolean existsInDb;
	private Exam exam;
	private int testId;
	private int questionId;
	private String uploadedMessage;
	
	// Constructors
	public Read() {
		this.jsonFile = null;
		this.exam = null;
		this.uploadedMessage = "";
	}
	public Read(String uploadedMessage) {
		this.jsonFile = null;
		this.exam = null;
		this.uploadedMessage = uploadedMessage;
	}	
	public Read(MultipartFile jsonFile) {		
		this.jsonFile = jsonFile;
		this.uploadedMessage = "";
		try {
			byte[] bytes = jsonFile.getBytes();
			FileOutputStream fileOuputStream = new FileOutputStream("exam.json");
			fileOuputStream.write(bytes);
			fileOuputStream.close();
			File file = new File("exam.json");			
			ObjectMapper mapper = new ObjectMapper();
			com.harpy.hag.upload.exam.Exam jExam;
			try { jExam = mapper.readValue(file, com.harpy.hag.upload.exam.Exam.class); }
			catch (JsonParseException e) { jExam = null; } catch (JsonMappingException e) { jExam = null; }	catch (IOException e) { jExam = null; }			
			this.exam = Exam.newExamFromJson(jExam);
		} catch (IOException e1) {
			System.out.println("Cannot read json file");
		}
		if (this.exam != null) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String q = "from Exam where key = '" + this.exam.getKey() + "'";
			ArrayList<Exam> exams = new ArrayList<Exam>(session.createQuery(q).list());
			this.existsInDb = true;
			if (exams.size() == 0) { this.existsInDb = false; }
			this.exam.setTestsHM(Exam.generateTestsHM(this.exam.getTests()));
		}
	}
	public Read(MultipartFile jsonFile, String uploadedMessage) {		
		this.jsonFile = jsonFile;
		this.uploadedMessage = uploadedMessage;
		try {
			byte[] bytes = jsonFile.getBytes();
			FileOutputStream fileOuputStream = new FileOutputStream("exam.json");
			fileOuputStream.write(bytes);
			fileOuputStream.close();
			File file = new File("exam.json");			
			ObjectMapper mapper = new ObjectMapper();
			com.harpy.hag.upload.exam.Exam jExam;
			try { jExam = mapper.readValue(file, com.harpy.hag.upload.exam.Exam.class); }
			catch (JsonParseException e) { jExam = null; } catch (JsonMappingException e) { jExam = null; }	catch (IOException e) { jExam = null; }			
			this.exam = Exam.newExamFromJson(jExam);
		} catch (IOException e1) {
			System.out.println("Cannot read json file");
		}
		if (this.exam != null) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String q = "from Exam where key = '" + this.exam.getKey() + "'";
			ArrayList<Exam> exams = new ArrayList<Exam>(session.createQuery(q).list());
			this.existsInDb = true;
			if (exams.size() == 0) { this.existsInDb = false; }
			this.exam.setTestsHM(Exam.generateTestsHM(this.exam.getTests()));
		}
	}
	
	// Upload to DB
	public static boolean uploadToDb (Exam exam) {
		if (exam == null) { return false; }
		else {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			try {
				session.save(exam);
				session.getTransaction().commit();
				return true;
			} catch (Exception e) {
				// TODO: handle exception
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

	public int getTestId() {
		return testId;
	}

	public int getQuestionId() {
		return questionId;
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

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getUploadedMessage() {
		return uploadedMessage;
	}

	public void setUploadedMessage(String uploadedMessage) {
		this.uploadedMessage = uploadedMessage;
	}
		
}
