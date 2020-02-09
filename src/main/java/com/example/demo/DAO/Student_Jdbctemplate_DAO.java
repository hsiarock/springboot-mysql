package com.example.demo.DAO;

import java.util.List;

import com.example.demo.model.Student;

public interface Student_Jdbctemplate_DAO  {

	public boolean saveStudent(Student student);
	public List<Student> getStudents();
	public boolean deleteStudent(Student student);
	public boolean updateStudent(Student student);
	public Student getStudentByID(int student_id);

}
