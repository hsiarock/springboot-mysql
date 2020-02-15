package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.LookupStateCode;
import com.example.demo.service.StudentJdbctemplateService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author David Hsia
 */
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/jdbctemplate")
@Slf4j
public class ControllerUseJdbctemplate {

	@Autowired
	StudentJdbctemplateService jdbctemplateService;

	@Autowired
	LookupStateCode lookupStateCode;

	@GetMapping("students-list")
	public ResponseEntity<List<Student>> allstudents() {
		List<Student> stduentList = jdbctemplateService.getStudents();
		log.info("return student list size: " + stduentList.size());

		return new ResponseEntity<List<Student>>(stduentList, HttpStatus.OK);
	}

	@GetMapping("studentById/{studentid}")
	public ResponseEntity<Student> getStudentById(@PathVariable("studentid") int studentid) {
		Student student = jdbctemplateService.getStudentById(studentid);

		log.info("return student id: " + student.getStudentid());
		log.info("return student branch: " + student.getStudentbranch());
		log.info("return student email: " + student.getStudentemail());
		log.info("return student name: " + student.getStudentname());

		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@PostMapping("addStudent")
	public ResponseEntity<Void> addStudent(@RequestBody List<Student> studentList, UriComponentsBuilder builder) {

		if (jdbctemplateService.addStudent(studentList)) {
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/addStudent").build().toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@PostMapping("deleteStudent")
	public ResponseEntity<Void> deleteStudent(@RequestBody Student student, UriComponentsBuilder builder) {

		if (jdbctemplateService.deleteStudent(student)) {
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/deleteStudent").build().toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@PostMapping("updateStudent")
	public ResponseEntity<Void> updateStudent(@RequestBody Student student, UriComponentsBuilder builder) {

		if (jdbctemplateService.updateStudent(student)) {
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/updateStudent").build().toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@GetMapping("lookupStateCode/{statecode}")
	public ResponseEntity<String> getStudentById(@PathVariable("statecode") String statecode,
											UriComponentsBuilder builder) {
		if (lookupStateCode.check(statecode)) {
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/lookupStateCode").build().toUri());
			return new ResponseEntity<String>(headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
	}

}
