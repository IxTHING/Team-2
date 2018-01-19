package com.qa.team2.persistence;

public class Trainee {
	private String name;
	private int age;
	
	public Trainee(String name, int age) {
		super();
		this.name = name;
		this.age = age;
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
		return "Trainee [name=" + name + ", age=" + age + "]";
	}
	
	
	
}
