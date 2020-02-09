package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAO.Student_Jdbctemplate_DAO;
import com.example.demo.model.Student;

@Service
//@Transactional
public class Student_Jdbctemplate_serviceImpl  implements Student_Jdbctemplate_service {

	@Autowired
	private Student_Jdbctemplate_DAO studentJdbctemplatedao;

	@Override
	public List<Student> getStudents() {
		return studentJdbctemplatedao.getStudents();
	}

	@Override
	public Student getStudentByID(int student_id) {
		return studentJdbctemplatedao.getStudentByID(student_id);
	}
}

