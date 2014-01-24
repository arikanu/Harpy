package com.harpy.hag.utils;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class QueryUtil {

	@SuppressWarnings("unchecked")
	public static <T> List<T> select(Class<T> type, String query) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ArrayList<Object> entities = new ArrayList<Object>(session.createQuery(query).list());		
		Function<Object, T> objToT = new Function<Object, T>() {
			public T apply(Object b) {
				return (T) b;
			}
		};
		List<T> newList = Lists.transform(entities, objToT);	
		return newList;
    }
	
	public static <T> T selectFirst(Class<T> type, String query) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ArrayList<Object> entities = new ArrayList<Object>(session.createQuery(query).list());		
		Function<Object, T> objToT = new Function<Object, T>() {
			public T apply(Object b) {
				return (T) b;
			}
		};
		List<T> newList = Lists.transform(entities, objToT);
		if (newList == null) {
			return null;
		} else if (newList.size() == 0) {
			return null;
		} else {
			return newList.get(0);
		}
	}
}
