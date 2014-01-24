package com.harpy.hag.vm.student.exam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.harpy.hag.db.entities.exam.Choice;
import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.db.entities.exam.Question;
import com.harpy.hag.db.entities.exam.Test;
import com.harpy.hag.db.entities.user.User;
import com.harpy.hag.db.entities.user_exam.ExamSolution;
import com.harpy.hag.db.entities.user_exam.QuestionSolution;
import com.harpy.hag.utils.HibernateUtil;
import com.harpy.hag.utils.JsonUtil;
import com.harpy.hag.vm.ImpVMHidden;
import com.harpy.hag.vm.VMHidden;


public class Solve extends VMHidden implements ImpVMHidden {

	private int examId;
	private Exam exam;
	private Date startDate;
	private Date endDate;
	private int[][] questionIndices;
	private String[][] questionViewTypes;		// view type olarak degilde Rows ve Columns iteratorleri, (r,c) ye map eden choiceIndex vs.
	private ArrayList<Integer> answers = new ArrayList<Integer>();
	
	public Solve() {
	}
	public Solve(int examId) {
		super("Sýnav Baþladý");
		this.examId = examId;
		this.exam = Exam.examById(examId);
		this.questionViewTypes = generateViewTypes(this.exam);
		this.startDate = new Date();
		this.questionIndices = new int[this.exam.getTests().size()][];
		int questionIndex = -1;
		for (int t = 0; t < this.exam.getTests().size(); t++) {
			Test test = this.exam.getTests().get(t);
			this.questionIndices[t] = new int[test.getQuestions().size()];
			for (int q = 0; q < test.getQuestions().size(); q++) {
				questionIndex = questionIndex + 1;
				this.questionIndices[t][q] = questionIndex;
				this.answers.add(-1);
			}
		}
		packHiddenObjects();
	}
	
	public Solve(String hiddenJson, ArrayList<Integer> answers, Date endDate) {
		extractHiddenObjects(hiddenJson);
		this.answers = answers;
		this.endDate = endDate;
	}
	
	private static String[][] generateViewTypes(Exam exam) {
		String[][] viewTypes = new String[exam.getTests().size()][];
		int t = -1;
		for (Test test : exam.getTests()) {			
			t = t + 1; int q = -1;
			viewTypes[t] = new String[test.getQuestions().size()];
			for (Question question : test.getQuestions()) {
				q = q + 1;
				String viewType = "";
				int prevLineIndex = 1;
				for (int c = 0; c < question.getChoices().size(); c++) {
					Choice choice = question.getChoices().get(c);
					if (choice.getChoiceLineIndex() == prevLineIndex) {
						viewType = viewType + choice.getChoiceLineIndex();
					} else {
						viewType = viewType + "_" + choice.getChoiceLineIndex();
					}
					viewTypes[t][q] = viewType;
				}				
			}
		}
		return viewTypes;
	}
	
	public static void saveExamSolution(Solve solved, String emailAddress) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Exam exam = Exam.examById(solved.getExamId());
		User user = User.userByEmailAddress(emailAddress);
		Date startDate = solved.getStartDate();
		Date endDate = solved.getEndDate();
		ExamSolution examSolution = new ExamSolution(exam, user, startDate, endDate);
		session.save(examSolution);
		int qCounter = -1;		
		ArrayList<Integer> answers = solved.getAnswers();
		int nAnswers = answers.size();
		for (Test test : exam.getTests()) {
			for (Question question : test.getQuestions()) {
				qCounter = qCounter + 1;
				int choiceIndex = -1;
				if (qCounter < nAnswers) {
					if (answers.get(qCounter) != null) {
						choiceIndex = answers.get(qCounter);
					}					
				}
				QuestionSolution questionSolution = new QuestionSolution(question, examSolution, choiceIndex);
				session.save(questionSolution);
			}
		}
		
		session.getTransaction().commit();
	}
	

	@Override
	public void packHiddenObjects() {
		ArrayList<Object> hiddenObjects = new ArrayList<Object>();
		hiddenObjects.add(this.examId);
		hiddenObjects.add(this.startDate);
		String hiddenJson = VMHidden.generateHiddenJson(hiddenObjects);
		this.setHiddenJson(hiddenJson);
	}

	@Override
	public void extractHiddenObjects(String hiddenJson) {
		String[] jsons = hiddenJson.split(VMHidden.delimiter);
		String jExamId = jsons[0];		
		String jStartTime = jsons[1];
		int examId = (Integer) JsonUtil.jsonToObj(jExamId, int.class); 
		Date startTime = (Date) JsonUtil.jsonToObj(jStartTime, Date.class);
		this.examId = examId;
		this.startDate = startTime;
	}
	
	
	
	public int getExamId() {
		return examId;
	}
	public Exam getExam() {
		return exam;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public int[][] getQuestionIndices() {
		return questionIndices;
	}
	public ArrayList<Integer> getAnswers() {
		return answers;
	}
	public String[][] getQuestionViewTypes() {
		return questionViewTypes;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setQuestionIndices(int[][] questionIndices) {
		this.questionIndices = questionIndices;
	}
	public void setAnswers(ArrayList<Integer> answers) {
		this.answers = answers;
	}
	public void setQuestionViewTypes(Exam exam) {
		int t = -1;
		for (Test test : exam.getTests()) {			
			t = t + 1; int q = -1;
			this.questionViewTypes[t] = new String[test.getQuestions().size()];
			for (Question question : test.getQuestions()) {
				q = q + 1;
				String viewType = "";
				int prevLineIndex = 1;
				for (int c = 0; c < question.getChoices().size(); c++) {
					Choice choice = question.getChoices().get(c);
					if (choice.getChoiceLineIndex() == prevLineIndex) {
						viewType = viewType + choice.getChoiceLineIndex();
					} else {
						viewType = viewType + "_" + choice.getChoiceLineIndex();
					}
					this.questionViewTypes[t][q] = viewType;
				}
			}
		}
	}
	//
	public String getStrStartTime() {
		DateFormat df = new SimpleDateFormat("hh:mm");
		return df.format(this.startDate);
	}
	public String getStrExpectedEndTime() {
		
		long start = this.getStartDate().getTime();
		Date expectedEndDate = new Date(start + this.exam.getDuration() * 60000);		
		DateFormat df = new SimpleDateFormat("hh:mm");
		return df.format(expectedEndDate);
	}
	
}
