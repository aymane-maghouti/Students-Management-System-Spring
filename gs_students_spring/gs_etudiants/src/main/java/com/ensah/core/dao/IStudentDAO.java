package com.ensah.core.dao;

import java.util.List;

import com.ensah.core.bo.Student;

public interface IStudentDAO {
	
	public Student save(Student s);
	public void delete(Long id);
	public Student getById(Long id);
	public void update(Student s);
	public List<Student> getAll();
	

}
