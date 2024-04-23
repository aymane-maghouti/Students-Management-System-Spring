package com.ensah.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ensah.core.bo.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


@Repository
public class StudentDaoIml implements IStudentDAO {

	@Autowired
	private SessionFactory sf;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	protected Session getSession() {
		return sf.getCurrentSession();
	}

	
	@Override
	public Student save(Student st) {
		Session s = sf.getCurrentSession();

		s.persist(st);

		return st;
	}

	@Override
	public void delete(Long id) {
		Session s = sf.getCurrentSession();
		
		Student st = getById(id);
		
		s.remove(st);
	}

	@Override
	public Student getById(Long id) {
		Session s = sf.getCurrentSession();
		
		return s.get(Student.class,id);
	}

	@Override
	public void update(Student st) {
		Session s = sf.getCurrentSession();

		s.merge(st);
	}

	@Override
	public List<Student> getAll() {	
	    Query query = entityManager.createQuery("SELECT s FROM Student s");		
		return  query.getResultList();
	}

}
