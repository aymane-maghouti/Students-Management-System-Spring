package com.ensah.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Student;
import com.ensah.core.dao.IStudentDAO;


@Service
@Transactional
public class StudentServiceIml implements IStudentService {

	@Autowired
	IStudentDAO iStudentDAO;
	
	@Override
	public void addStudent(Student s) {
		iStudentDAO.save(s);
	}

	@Override
	public void updateStudent(Student s) {

		iStudentDAO.update(s);
	}

	@Override
	public Student getStudentById(Long id) {

		return iStudentDAO.getById(id);
	}

	@Override
	public List<Student> getAllStudents() {
		
		
		return iStudentDAO.getAll();
	}

	@Override
	public void deleteStudent(Long id) {
		
		iStudentDAO.delete(id);
	}

}
