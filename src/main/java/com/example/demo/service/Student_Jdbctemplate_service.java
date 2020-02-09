package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Student;

public interface Student_Jdbctemplate_service {
	
	//public boolean saveStudent(Student student);
	public List<Student> getStudents();
	//public boolean deleteStudent(Student student);
	public Student getStudentByID(int student_id);
	//public boolean updateStudent(Student student);
}
