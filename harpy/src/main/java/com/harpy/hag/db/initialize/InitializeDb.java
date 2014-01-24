package com.harpy.hag.db.initialize;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.harpy.hag.db.entities.exam.Choice;
import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.db.entities.exam.ExamMasterType;
import com.harpy.hag.db.entities.exam.ExamSubType;
import com.harpy.hag.db.entities.exam.Question;
import com.harpy.hag.db.entities.exam.Test;
import com.harpy.hag.db.entities.user.Role;
import com.harpy.hag.db.entities.user.User;
import com.harpy.hag.db.entities.user_exam.ExamSolution;
import com.harpy.hag.db.entities.user_exam.QuestionSolution;
import com.harpy.hag.utils.HibernateUtil;


public class InitializeDb {

	public static void main(String[] args) {
		
		// CREATE TABLES	
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(Role.class);
		config.addAnnotatedClass(ExamMasterType.class);
		config.addAnnotatedClass(ExamSubType.class);
		config.addAnnotatedClass(Exam.class);
		config.addAnnotatedClass(Test.class);
		config.addAnnotatedClass(Question.class);
		config.addAnnotatedClass(ExamSolution.class);
		config.addAnnotatedClass(QuestionSolution.class);
		config.configure("hibernate.cfg.xml");
		new SchemaExport(config).create(true, true);			
		
		// ADD SAMPLE DATA
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		// USER
		User uur = new User();
		uur.setEmailAddress("u@a.com");
		uur.setFirstName("Ugur");
		uur.setLastName("Arikan");
		uur.setPassword("pass");		
		session.save(uur);
		User baco = new User();
		baco.setEmailAddress("h@b.com");
		baco.setFirstName("Huseyin");
		baco.setLastName("Bacanak");
		baco.setPassword("pass");
		session.save(baco);
		User ikiz = new User();
		ikiz.setEmailAddress("u@g.com");
		ikiz.setFirstName("Umut");
		ikiz.setLastName("Gumus");
		ikiz.setPassword("pass");
		session.save(ikiz);
		
		// ROLE
		Role admin = new Role();
		admin.setRoleName("ADMN");
		admin.getUsers().add(uur);
		admin.getUsers().add(ikiz);
		session.save(admin);
		Role student = new Role();
		student.setRoleName("STDNT");
		student.getUsers().add(uur);
		student.getUsers().add(baco);
		session.save(student);	
		
		
		// EXAM MASTER TYPE
		ExamMasterType oss = new ExamMasterType();
		oss.setCode("OSS");
		oss.setName("Ogrenci Secme Yerlestirme Sinav");
		session.save(oss);
		
		ExamMasterType kpss = new ExamMasterType();
		kpss.setCode("KPSS");
		kpss.setName("Kamu Personeli Secme Sinavi");
		session.save(kpss);
		
		// EXAM SUB TYPE
		ExamSubType ossSub = new ExamSubType();
		ossSub.setName("OSS");
		ossSub.setCode("OSS");
		ossSub.setExamMasterType(oss);
		session.save(ossSub);
		
		ExamSubType oo = new ExamSubType();
		oo.setCode("OO");
		oo.setName("Ortaogretim");
		oo.setExamMasterType(kpss);
		session.save(oo);
		
		ExamSubType ol = new ExamSubType();
		ol.setCode("OL");
		ol.setName("On Lisans");
		ol.setExamMasterType(kpss);
		session.save(ol);
		
		ExamSubType l = new ExamSubType();
		l.setCode("L");
		l.setName("Lisans");
		l.setExamMasterType(kpss);
		session.save(l);
		
		// EXAM
		Exam kpss2013oo = new Exam();
		kpss2013oo.setKey("kpss2013oo");
		kpss2013oo.setNbOfQuestions(120);
		kpss2013oo.setDuration(120);
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");		
		try {
			kpss2013oo.setDate(formatter.parse("2013-05-05"));
		} catch (ParseException e) {
			kpss2013oo.setDate(null);
		}
		kpss2013oo.setExamSubType(oo);
		session.save(kpss2013oo);
		
		// TEST
		Test gy = new Test();
		gy.setName("Genel Yetenek");
		gy.setNumber(1);
		gy.setNbOfQuestions(60);
		gy.setExam(kpss2013oo);
		session.save(gy);
		
		Test gk = new Test();
		gk.setName("Genel Kultur");
		gk.setNumber(2);
		gk.setNbOfQuestions(60);
		gk.setExam(kpss2013oo);
		session.save(gk);
		
		// QUESTION
		Question q1 = new Question();
		q1.setNumber(1);
		q1.setQuestionHtml("soru1");
		q1.setCorrectAnswer("A");
		q1.setTest(gy);
		session.save(q1);
		
		Question q2 = new Question();
		q2.setNumber(2);
		q2.setQuestionHtml("soru2");
		q2.setCorrectAnswer("A");
		q2.setTest(gy);
		session.save(q2);
		
		Question q3 = new Question();
		q3.setNumber(1);
		q3.setQuestionHtml("soru1");
		q3.setCorrectAnswer("A");
		q3.setTest(gk);
		session.save(q3);
		
		// CHOICE
		Choice c1 = new Choice();
		c1.setChoiceCode("A");
		c1.setChoiceHtml("A sikki");
		c1.setChoiceLineIndex(1);
		c1.setCorrectAnswer(true);
		c1.setQuestion(q1);
		session.save(c1);
		
		Choice c2 = new Choice();
		c2.setChoiceCode("B");
		c2.setChoiceHtml("B sikki");
		c2.setChoiceLineIndex(1);
		c2.setCorrectAnswer(true);
		c2.setQuestion(q1);
		session.save(c2);
		
		Choice c3 = new Choice();
		c3.setChoiceCode("A");
		c3.setChoiceHtml("A sikki");
		c3.setChoiceLineIndex(1);
		c3.setCorrectAnswer(true);
		c3.setQuestion(q2);
		session.save(c3);
		
		// EXAMSOLUTION
		ExamSolution examSolution = new ExamSolution();
		examSolution.setUser(uur);
		examSolution.setExam(kpss2013oo);
		examSolution.setStartDate(new Date());
		examSolution.setEndDate(new Date());
		session.save(examSolution);
		
		ExamSolution examSolution2 = new ExamSolution();
		examSolution2.setUser(uur);
		examSolution2.setExam(kpss2013oo);
		examSolution2.setStartDate(new Date());
		examSolution2.setEndDate(new Date());
		session.save(examSolution2);
		
		ExamSolution examSolution3 = new ExamSolution();
		examSolution3.setUser(baco);
		examSolution3.setExam(kpss2013oo);
		examSolution3.setStartDate(new Date());
		examSolution3.setEndDate(new Date());
		session.save(examSolution3);
		
		// QUESTIONSOLUTION
		QuestionSolution qS1 = new QuestionSolution();
		qS1.setExamSolution(examSolution);
		qS1.setQuestion(q1);
		qS1.setChoiceIndex(0);
		session.save(qS1);
		
		QuestionSolution qS2 = new QuestionSolution();
		qS2.setExamSolution(examSolution);
		qS2.setQuestion(q2);
		qS2.setChoiceIndex(-1);
		session.save(qS2);
		
									
		//*/
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();

	}

}
