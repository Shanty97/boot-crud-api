package com.boot.crud.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.crud.api.models.Student;
import com.boot.crud.api.storage.MiniStorage;

@Service
public class StudentService {

	@Autowired
	private MiniStorage miniStorage;

	public StudentService(MiniStorage miniStorage) {
		miniStorage.create(
				new Student((int) Math.round(Math.random() * 100), 
				"John Doe",
				(int) Math.round(Math.random() * 30) + 1));
		miniStorage.create(
				new Student((int) Math.round(Math.random() * 100), 
				"Drek L", 
				(int) Math.round(Math.random() * 30) + 1));
		miniStorage.create(new Student((int) Math.round(Math.random() * 100), 
				"Jimmy John",
				(int) Math.round(Math.random() * 30) + 1));
	}

	public void addToDatabase(Student data) {
		miniStorage.create(data);
	}

	public List<Student> filterAll() {
		return miniStorage.findAll().stream().collect(Collectors.toList());
	}

	public Student filterById(int id) {
		return miniStorage.findById(id);
	}
	
	public List<Student> filterByAge(int age) {
		return miniStorage.findByAge(age);
	}

}
