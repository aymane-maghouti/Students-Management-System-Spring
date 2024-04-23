package com.ensah.core.services;

import java.util.List;


import com.ensah.core.bo.Student;



public interface IStudentService {


	
	public void addStudent(Student s);
	
	public void updateStudent(Student s);
	
	public Student getStudentById(Long id);
	
	public List<Student> getAllStudents();
	
	public void deleteStudent(Long id);
	
	
	
	
}
