package com.example.demo.service;

import com.example.demo.dao.StudentJdbctemplateDAO;
import com.example.demo.model.Student;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David Hsia
 */
@Service
//@Transactional
public class StudentJdbctemplateServiceImpl implements StudentJdbctemplateService {

	@Autowired
	private StudentJdbctemplateDAO studentJdbctemplatedao;

	@Override
	public List<Student> getStudents() {
		return studentJdbctemplatedao.getStudents();
	}

	@Override
	public Student getStudentById(int studentId) {
		return studentJdbctemplatedao.getStudentById(studentId);
	}
}