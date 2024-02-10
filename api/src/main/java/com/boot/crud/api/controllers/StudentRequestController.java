package com.boot.crud.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boot.crud.api.models.Student;
import com.boot.crud.api.services.StudentService;

@RestController()
@RequestMapping("/api/v1")
public class StudentRequestController {

	@Autowired
	private StudentService studentService;

	/**
	 * 
	 * Test API with message
	 * 
	 */
	@GetMapping("/test")
	public String getMessage() {
		return "Hello World";
	}

	/**
	 * 
	 * Get API - To get all students
	 * 
	 */
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getStudents() {
		return new ResponseEntity<>(studentService.filterAll(), HttpStatus.OK);
	}

	/**
	 * 
	 * Get API - To get a student with mentioned id
	 * 
	 */
	@GetMapping("/student/id/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable UUID id) {
		Student student = studentService.filterById(id);
		if (student != null) {
			return new ResponseEntity<>(student, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	/**
	 * 
	 * Get API - To get student/students with mentioned age
	 * 
	 */
	@GetMapping("/student/age/{age}")
	public ResponseEntity<List<Student>> getStudentsByAge(@PathVariable int age) {
		return new ResponseEntity<>(studentService.filterByAge(age), HttpStatus.OK);
	}

	/**
	 * 
	 * Post API - To create a new student
	 * 
	 */
	@PostMapping("/student/add")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		studentService.addToDatabase(student);
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}

	/**
	 * 
	 * Post API - To create new students
	 * 
	 */
	@PostMapping("/student/addAll")
	public ResponseEntity<List<Student>> addStudents(@RequestBody List<Student> students) {
		studentService.addAllToDatabase(students);
		return new ResponseEntity<>(students, HttpStatus.CREATED);
	}

	/**
	 * 
	 * Update API - To update a new student
	 * 
	 */
	@PutMapping("/student/update")
	public ResponseEntity<Student> updateStudentName(@RequestBody Student student, @RequestParam("name") String name) {
		Student tempStudent = studentService.updateDatabase(student);
		if (tempStudent != null) {
			return new ResponseEntity<>(tempStudent, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

	}

	/**
	 * 
	 * Remove API - To remove a new student
	 * 
	 */
	@DeleteMapping("/student/remove")
	public ResponseEntity<Student> removeStudentById(@RequestParam("id") UUID id) {
		Student tempStudent = studentService.removeStudentById(id);
		if (tempStudent != null) {
			return new ResponseEntity<>(tempStudent, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

	}

}
