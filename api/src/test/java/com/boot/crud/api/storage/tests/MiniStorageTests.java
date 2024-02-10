package com.boot.crud.api.storage.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.crud.api.models.Student;
import com.boot.crud.api.storage.MiniStorage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MiniStorageTests {

	MiniStorage miniStorage = null;

	@BeforeEach
	public void testSetup() {
		// Create a new MiniStorage instance for each test
		miniStorage = new MiniStorage();
	}

	@Test
	public void createTest() {

		// Create a test student
		Student student = new Student("Adam Jones", 24);

		// Test create
		miniStorage.create(student);

		// Assert student was added to the map
		assertNotNull(miniStorage.findById(student.getId()));

		// Assert returned student is the same as the created one
		Student createdStudent = miniStorage.findById(student.getId());
		assertEquals(student, createdStudent); // Use assertSame for reference equality
	}

	@Test
	public void findAllTest() {

		// Create a test students
		List<Student> studentList = Arrays.asList(new Student("qwe", 20), new Student("asd", 29),
				new Student("cvb", 23), new Student("nmm", 24));

		// Test create
		for (Student s : studentList) {
			miniStorage.create(s);
		}

		// Test findAll
		List<Student> studentsFromStorage = miniStorage.findAll();

		// Assert students from miniStorage and the created studentlist
		assertTrue(studentsFromStorage.containsAll(studentList));

		// Assert id from studentlist and studentsFromStorage
		Set<UUID> fromStorageIds = studentsFromStorage.stream().map(Student::getId).collect(Collectors.toSet());
		Set<UUID> fromListIds = studentsFromStorage.stream().map(Student::getId).collect(Collectors.toSet());
		assertEquals(fromStorageIds, fromListIds);

	}

	@Test
	public void findByIdNotNullTest() {

		Student student = new Student("John Deo", 24);
		miniStorage.create(student);

		// Test findById
		Student searchedStudent = miniStorage.findById(student.getId());

		// Assert created and searched
		assertEquals(student, searchedStudent);

	}

	@Test
	public void findByIdNullTest() {

		// Test findById
		Student searchedStudent = miniStorage.findById(UUID.randomUUID());

		// Assert created and searched
		assertNull(searchedStudent);

	}

	@Test
	public void findByAgeTest() {

		// Create a test students
		List<Student> studentList = Arrays.asList(new Student("qwe", 20), new Student("asd", 29),
				new Student("cvb", 20), new Student("nmm", 24));

		// Test create
		for (Student s : studentList) {
			miniStorage.create(s);
		}

		List<UUID> searchedStudents = miniStorage.findByAge(20).stream().map(Student::getId).toList();

		// Assert the searched students using age
		assertTrue(searchedStudents.size() <= miniStorage.findAll().size());
		for (UUID s : searchedStudents) {
			assertNotNull(miniStorage.findById(s));
		}

	}

	@Test
	public void findByInvalidAgeTest() {

		// Create a test students
		List<Student> studentList = Arrays.asList(new Student("qwe", 20), new Student("asd", 29),
				new Student("cvb", 23), new Student("nmm", 24));

		// Test create
		for (Student s : studentList) {
			miniStorage.create(s);
		}

		List<Student> searchedStudents = miniStorage.findByAge(21);

		assertTrue(searchedStudents.isEmpty());
	}

	@Test
	public void removeByValidIdTest() {

		// Create a test students
		List<Student> studentList = Arrays.asList(new Student("qwe", 20), new Student("asd", 29),
				new Student("cvb", 23), new Student("nmm", 24));

		// Test create
		for (Student s : studentList) {
			miniStorage.create(s);
		}

		Student removedStudent = miniStorage.removeById(studentList.get(2).getId());

		long occurenceOfRemovedStudent = miniStorage.findAll().stream().filter(m -> m.getId() == removedStudent.getId())
				.count();

		assertEquals(occurenceOfRemovedStudent, 0);
		assertNull(miniStorage.removeById(UUID.randomUUID()));

	}

	@Test
	public void removeByInValidIdTest() {

		// Create a test students
		List<Student> studentList = Arrays.asList(new Student("qwe", 20), new Student("asd", 29),
				new Student("cvb", 23), new Student("nmm", 24));

		// Test create
		for (Student s : studentList) {
			miniStorage.create(s);
		}
		
		assertNull(miniStorage.removeById(UUID.randomUUID()));
	}

}
