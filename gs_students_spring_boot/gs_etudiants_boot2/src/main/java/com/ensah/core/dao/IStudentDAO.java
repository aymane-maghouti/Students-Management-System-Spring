package com.ensah.core.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.core.bo.Student;

public interface IStudentDAO extends JpaRepository<Student, Long> {
	

	

}
