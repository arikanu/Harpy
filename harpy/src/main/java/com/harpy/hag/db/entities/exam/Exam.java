package com.harpy.hag.db.entities.exam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import org.hibernate.Session;

import com.harpy.hag.db.utils.HibernateUtil;

@Entity
public class Exam {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="examId")
	@TableGenerator(name="examId", table="pkExam", pkColumnName="examKey", pkColumnValue="examValue", allocationSize=1)
	private int examId;
	
	@Column(nullable=false)
	private String key;
	
	@Column(nullable=false)
	private int nbOfQuestions;
	
	@Column(nullable=false)
	private int duration;
	
	@Column(nullable=false)
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
	private HashMap<Integer, String> testsHM;// = generateTestsHM(this.tests);
	
	public static HashMap<Integer, String> generateTestsHM(List<Test> tests) {
		HashMap<Integer, String> hm = new  HashMap<Integer, String>();
		for (int i = 0; i < tests.size(); i++) {
			hm.put(i, tests.get(i).getName());
		}
		return hm;
	}
	
	public static Exam newExamFromJson(com.harpy.hag.upload.exam.Exam jExam) {
		if (jExam == null) {
			System.out.println("jexam is NULL");
			return null;			
		} else {
			System.out.println("jexam is NOT NULL");
			Exam exam = new Exam();
			exam.setKey(jExam.getKey());		
			exam.setDuration(jExam.getDuration());
			try {
				DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
				exam.setDate(formatter.parse(jExam.getDate()));
			} catch (Exception e) {
				exam.setDate(null);
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
						choice.setChoiceId(c);
						choice.setChoiceLineIndex(choicesLineIndex.get(c));
						choices.add(choice);
					}
					try { choices.get(0).setChoiceCode("A"); } catch (Exception e) { }
					try { choices.get(1).setChoiceCode("B"); } catch (Exception e) { }
					try { choices.get(2).setChoiceCode("C"); } catch (Exception e) { }
					try { choices.get(3).setChoiceCode("D"); } catch (Exception e) { }
					try { choices.get(4).setChoiceCode("E"); } catch (Exception e) { }
					try { choices.get(0).setCorrectAnswer(question.getCorrectAnswer() == "A"); } catch (Exception e) { }
					try { choices.get(1).setCorrectAnswer(question.getCorrectAnswer() == "B"); } catch (Exception e) { }
					try { choices.get(2).setCorrectAnswer(question.getCorrectAnswer() == "C"); } catch (Exception e) { }
					try { choices.get(3).setCorrectAnswer(question.getCorrectAnswer() == "D"); } catch (Exception e) { }
					try { choices.get(4).setCorrectAnswer(question.getCorrectAnswer() == "E"); } catch (Exception e) { }
					question.setChoices(choices);
					questions.add(question);
				}
				test.setQuestions(questions);
				tests.add(test);
			}
			String subTypeCode = jExam.getSubType();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String q = "from ExamSubType where code = '" + subTypeCode + "'";
			ArrayList<ExamSubType> examSubTypes = new ArrayList<ExamSubType>(session.createQuery(q).list());
			try {
				exam.setExamSubType(examSubTypes.get(0));
			} catch (Exception e) {
				exam.setExamSubType(null);
			}
			List<ExamImage> examImages = new ArrayList<ExamImage>();
			for (byte[] jImage : jExam.getImages()) {
				ExamImage examImage = new ExamImage();
				examImage.setImage(jImage);
				examImages.add(examImage);
			}
			exam.setImages(examImages);
			exam.setTests(tests);
			exam.setNbOfQuestions(nbOfQuestions);
			return exam;
		}		
	}
	
	
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
	public String getStrDate() {
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		return formatter.format(date);
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

	public HashMap<Integer, String> getTestsHM() {
		return testsHM;
	}

	public void setTestsHM(HashMap<Integer, String> testsHM) {
		this.testsHM = testsHM;
	}
	
}
