package com.example.demo.dao;

import com.example.demo.model.StateCode;
import com.example.demo.model.Student;
import java.util.List;

/**
 *
 * @author David Hsia
 */
public interface StudentJdbctemplateDAO  {

	boolean saveStudent(List<Student> students);
	List<Student> getStudents();
	boolean deleteStudent(Student student);
	boolean updateStudent(Student student);
	Student getStudentById(int studentid);
	int getStudentBynameBranch(String name, String branch);
	List<StateCode> getStateCode();
}
