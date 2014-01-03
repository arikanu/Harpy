package com.harpy.hag.db.initialize;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.db.entities.exam.ExamMasterType;
import com.harpy.hag.db.entities.exam.ExamSubType;
import com.harpy.hag.db.entities.exam.Question;
import com.harpy.hag.db.entities.exam.Test;
import com.harpy.hag.db.entities.user.Role;
import com.harpy.hag.db.entities.user.User;


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
									
		//*/
		session.getTransaction().commit();

	}

}
