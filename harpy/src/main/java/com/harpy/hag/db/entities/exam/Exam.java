package com.harpy.hag.db.entities.exam;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javax.persistence.Transient;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;

import com.harpy.hag.utils.HibernateUtil;
import com.harpy.hag.utils.QueryUtil;

@Entity
public class Exam {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="examId")
	@TableGenerator(name="examId", table="pkExam", pkColumnName="examKey", pkColumnValue="examValue", allocationSize=1)
	private int examId;
	
	@Column(nullable=true)
	private String key;
	
	@Column(nullable=true)
	private int nbOfQuestions;
	
	@Column(nullable=true)
	private int duration;
	
	@Column(nullable=true)
	private Date date;
	
	@OneToMany(targetEntity=Test.class, mappedBy="exam", cascade=CascadeType.ALL/*, fetch=FetchType.EAGER*/)
	private List<Test> tests;	
	
	@OneToMany(targetEntity=ExamImage.class, mappedBy="exam", cascade=CascadeType.ALL/*, fetch=FetchType.EAGER*/)
	private List<ExamImage> images;
	
	@ManyToOne
	@JoinColumn(name="examSubTypeId")
	private ExamSubType examSubType;

	// non-db properties
	@Transient
	private ArrayList<String> choiceCodes = new ArrayList<String>() {{ add("A"); add("B"); add("C"); add("D"); add("E"); }};
	
	
	public Exam(){}
	public Exam(com.harpy.hag.upload.exam.Exam jExam) {
		if (jExam != null) {
			this.key = jExam.getKey();
			this.duration = jExam.getDuration();
			try {
				DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
				this.date = formatter.parse(jExam.getDate());
			} catch (Exception e) {
				this.date = null;
			}
			List<Test> tests = new ArrayList<Test>();
			List<com.harpy.hag.upload.exam.Test> jTests = jExam.getTests();
			int nbOfQuestions = 0;
			for (int t = 0; t < jTests.size(); t++) {
				Test test = new Test();
				com.harpy.hag.upload.exam.Test jTest = jTests.get(t);
				test.setName(jTest.getName());			
				test.setNumber(jTest.getNumber());
				List<Question> questions = new ArrayList<Question>();
				List<com.harpy.hag.upload.exam.Question> jQuestions = jTest.getQuestions();
				nbOfQuestions = nbOfQuestions + jQuestions.size();
				test.setNbOfQuestions(jQuestions.size());
				for (int q = 0; q < jQuestions.size(); q++) {
					Question question = new Question();
					com.harpy.hag.upload.exam.Question jQuestion = jQuestions.get(q);
					question.setCorrectAnswer(jQuestion.getCorrectAnswer());
					question.setNumber(jQuestion.getNumber());
					question.setQuestionHtml(jQuestion.getQuestionHtml());
					List<Choice> choices = new ArrayList<Choice>();
					List<String> choicesHtml = jQuestion.getChoicesHtml();
					List<Integer> choicesLineIndex = jQuestion.getChoicesLineIndex();
					for (int c = 0; c < choicesHtml.size(); c++) {
						Choice choice = new Choice();
						choice.setChoiceHtml(choicesHtml.get(c));
						choice.setChoiceCode(this.choiceCodes.get(c));
						choice.setChoiceLineIndex(choicesLineIndex.get(c));
						choice.setCorrectAnswer(question.getCorrectAnswer() == choice.getChoiceCode());
						choices.add(choice);
					}
					question.setChoices(choices);
					questions.add(question);
				}
				test.setQuestions(questions);
				tests.add(test);
			}
			List<ExamImage> examImages = new ArrayList<ExamImage>();
			for (byte[] jImage : jExam.getImages()) {
				ExamImage examImage = new ExamImage();
				examImage.setImage(jImage);
				examImages.add(examImage);
			}
			this.images = examImages;
			this.tests = tests;
			this.nbOfQuestions = nbOfQuestions;
		}
	}
	
	public static Exam examById(int examId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String query = "from Exam where examId = " + examId;
		return QueryUtil.selectFirst(Exam.class, query);
	}
	
	public static List<Exam> populateExams(int subTypeId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String query = "from Exam where examSubTypeId = " + subTypeId;
		return QueryUtil.select(Exam.class, query);
	}
	
	/*
	public static Exam examFromJson(String examJson) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Exam exam = mapper.readValue(examJson, Exam.class);
			return exam;
		} catch (JsonParseException e) {
			return null;
		} catch (JsonMappingException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	public static String jsonFromExam(Exam exam) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(exam);
		} catch (JsonGenerationException e) {
			return null;
		} catch (JsonMappingException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	*/
	
	
	// Getters & Setters
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getNbOfQuestions() {
		return nbOfQuestions;
	}
	public void setNbOfQuestions(int nbOfQuestions) {
		this.nbOfQuestions = nbOfQuestions;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Date getDate() {
		return date;
	}	
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Test> getTests() {
		return tests;
	}
	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
	public List<ExamImage> getImages() {
		return images;
	}
	public void setImages(List<ExamImage> images) {
		this.images = images;
	}
	public ExamSubType getExamSubType() {
		return examSubType;
	}
	public void setExamSubType(ExamSubType examSubType) {
		this.examSubType = examSubType;
	}
	

	public String generateStrDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(this.date);
	}

	
}
