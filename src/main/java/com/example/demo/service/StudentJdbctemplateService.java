package com.example.demo.service;

import com.example.demo.model.Student;
import java.util.List;

/**
 * @author David Hsia
 */
public interface StudentJdbctemplateService {

	//boolean saveStudent(Student student);
	List<Student> getStudents();
	//public boolean deleteStudent(Student student);
	Student getStudentById(int studentId);
	//boolean updateStudent(Student student);
}
