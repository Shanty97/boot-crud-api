package com.boot.crud.api.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.crud.api.models.Student;
import com.boot.crud.api.storage.MiniStorage;

@Service
public class StudentService {

	@Autowired
	private MiniStorage miniStorage;

	public Student addToDatabase(Student data) {
		return miniStorage.create(data);
	}

	public List<Student> addAllToDatabase(List<Student> students) {
		for (Student student : students) {
			addToDatabase(student);
		}
		return students;
	}

	public Student updateDatabase(Student student) {
		Student tempStudent = miniStorage.findById(student.getId());
		if (tempStudent != null) {
			return miniStorage.create(tempStudent);
		}
		return null;
	}

	public List<Student> filterAll() {
		return miniStorage.findAll().stream().collect(Collectors.toList());
	}

	public Student filterById(UUID id) {
		return miniStorage.findById(id);
	}

	public List<Student> filterByAge(int age) {
		return miniStorage.findByAge(age);
	}

	public Student removeStudentById(UUID id) {
		Student tempStudent = miniStorage.findById(id);
		if (tempStudent != null) {
			return miniStorage.removeById(tempStudent.getId());
		}
		return null;
	}

}
