package com.harpy.hag.db.initialize;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.functors.IfClosure;
import org.hibernate.Session;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.harpy.hag.db.entities.exam.Exam;
import com.harpy.hag.db.entities.user.Role;
import com.harpy.hag.db.entities.user.User;
import com.harpy.hag.utils.HibernateUtil;
import com.harpy.hag.utils.QueryUtil;

public class TrySelect {

	
	//private static SessionFactory factory = null;
	//private static Session session = null;
	
	public static void main(String[] args) {
		//*
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String q = "from User";
		
		ArrayList<User> userList = new ArrayList<User>(session.createQuery(q).list()); 
		for(User user : userList)
		{
			System.out.println("FirstName: "+user.getFirstName());
			System.out.println("LastName: "+user.getLastName());
			System.out.println("Email: "+user.getEmailAddress());
			System.out.println("Password: "+user.getPassword());
			for(Role role : user.getRoles()) {
				System.out.println("  Role:" + role.getRoleName());
			}
		} 
		
		//*/
		
		//*
		//Session session2 = HibernateUtil.getSessionFactory().getCurrentSession();
		//session2.beginTransaction();
		
		String q2 = "from Exam";
		
		ArrayList<Exam> examList1 = new ArrayList<Exam>(session.createQuery(q2).list()); 
		for(Exam exam : examList1)
		{
			System.out.println("ExamId: "+exam.getExamId());
			System.out.println("Date: "+exam.getDate());
		}
		
		
		
		String q3 = "from Exam where examSubTypeId=2";
		ArrayList<Exam> examList2 = new ArrayList<Exam>(session.createQuery(q3).list()); 
		for(Exam exam : examList2)
		{
			System.out.println("ExamId: "+exam.getExamId());
			System.out.println("Date: "+exam.getDate());
		}
		
		
		

		
		String q4 = "select min(examSubTypeId) from ExamSubType";
		ArrayList<Integer> minSubTypeIds = new ArrayList<Integer>(session.createQuery(q4).list());
		System.out.println("min subtypeid: " + minSubTypeIds.get(0));
				
		
		
		
		
		String query = "from Exam where examSubTypeId=2";
		List<Exam> exo = QueryUtil.select(Exam.class, query);		
		System.out.println("exo: " + exo.get(0).getDuration());
		
		
		session.getTransaction().commit(); 
		HibernateUtil.getSessionFactory().close();
		
		
	}
	

}
