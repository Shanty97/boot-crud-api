package com.boot.crud.api.models;

import java.util.UUID;

public class Student {

	private UUID id;
	private String name;
	private int age;

	public Student(String name, int age) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.age = age;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
