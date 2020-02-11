package com.example.demo.dao;

import com.example.demo.model.Student;
import java.util.List;

/**
 *
 * @author David Hsia
 */
public interface StudentJdbctemplateDAO  {

	boolean saveStudent(Student student);
	List<Student> getStudents();
	boolean deleteStudent(Student student);
	boolean updateStudent(Student student);
	Student getStudentById(int studentId);

}
