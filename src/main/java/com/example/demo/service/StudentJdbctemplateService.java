package com.example.demo.service;

import com.example.demo.model.Student;
import java.util.List;

/**
 * @author David Hsia
 */
public interface StudentJdbctemplateService {

	boolean addStudent(List<Student> studentList);
	List<Student> getStudents();
	boolean deleteStudent(Student student);
	Student getStudentById(int studentId);
	boolean updateStudent(Student student);
}
