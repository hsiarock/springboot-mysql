package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.service.Student_Jdbctemplate_service;

@RestController
//@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/jdbctemplate")
public class ControllerUseJdbctemplate {
	
	@Autowired
	Student_Jdbctemplate_service jdbctemplate_service;

	@GetMapping("students-list")
	public List<Student> allstudents() {
		 return jdbctemplate_service.getStudents();
	}
	
	@GetMapping("studentById/{student_id}")
	public Student getStudentById(@PathVariable("student_id") int student_id) {
		 return jdbctemplate_service.getStudentByID(student_id);
		 
	}
}
