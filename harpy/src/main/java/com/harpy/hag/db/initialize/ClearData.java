package com.harpy.hag.db.initialize;

import org.hibernate.Query;
import org.hibernate.Session;
import com.harpy.hag.utils.HibernateUtil;

public class ClearData {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query qQuestionSolution = session.createQuery("delete QuestionSolution");
		qQuestionSolution.executeUpdate();
		
		Query qExamSolution = session.createQuery("delete ExamSolution");
		qExamSolution.executeUpdate();
		
		Query qChoice = session.createQuery("delete Choice");
		qChoice.executeUpdate();
		
		Query qQuestion = session.createQuery("delete Question");
		qQuestion.executeUpdate();
		
		Query qTest = session.createQuery("delete Test");
		qTest.executeUpdate();
		
		Query qExamImage = session.createQuery("delete ExamImage");
		qExamImage.executeUpdate();
		
		Query qExam = session.createQuery("delete Exam");
		qExam.executeUpdate();
		
		Query qExamSubType = session.createQuery("delete ExamSubType");
		qExamSubType.executeUpdate();
		
		Query qExamMasterType = session.createQuery("delete ExamMasterType");
		qExamMasterType.executeUpdate();		
				
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();

	}

}
