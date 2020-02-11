package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentJdbctemplateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author David Hsia
 */
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/jdbctemplate")
public class ControllerUseJdbctemplate {

	@Autowired
	StudentJdbctemplateService jdbctemplateService;

	@GetMapping("students-list")
	public List<Student> allstudents() {
		 return jdbctemplateService.getStudents();
	}

	@GetMapping("studentById/{studentId}")
	public Student getStudentById(@PathVariable("studentId") int studentId) {
		 return jdbctemplateService.getStudentById(studentId);

	}
}
