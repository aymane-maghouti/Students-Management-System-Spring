package com.ensah.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensah.core.bo.Student;
import com.ensah.core.dao.IStudentDAO;



public interface IStudentService {


	
	public void addStudent(Student s);
	
	public void updateStudent(Student s);
	
	public Student getStudentById(Long id);
	
	public List<Student> getAllStudents();
	
	public void deleteStudent(Long id);
	
	
	
	
}
