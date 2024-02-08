package com.boot.crud.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.crud.api.models.Student;
import com.boot.crud.api.services.StudentService;
import com.boot.crud.api.storage.MiniStorage;

@RestController()
@RequestMapping("/api/v1")
public class StudentRequestController {
	
	@Autowired
	private StudentService studentService;
	
	/*
	 * 
	 * Test API with message 
	 * 
	 */
	@GetMapping("/test")
	public String getMessage() {
		return "Hello World";
	}
	
	/*
	 * 
	 * Get API - To get all students 
	 * 
	 */
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getStudents() {
		return new ResponseEntity<>(studentService.filterAll(), HttpStatus.OK);
	}
	
	/*
	 * 
	 * Get API - To get a student with mentioned id 
	 * 
	 */
	@GetMapping("/student/id/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable int id) {
		Student student = studentService.filterById(id); 
		if(student != null) {
			return new ResponseEntity<>(student, HttpStatus.OK); 
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	/*
	 * 
	 * Get API - To get student/students with mentioned age 
	 * 
	 */
	@GetMapping("/student/age/{age}")
	public ResponseEntity<List<Student>> getStudentsByAge(@PathVariable int age) {
		return new ResponseEntity<>(studentService.filterByAge(age), HttpStatus.OK);
	}
	
	
}
