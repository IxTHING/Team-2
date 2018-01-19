package com.qa.team2.persistence;

import java.util.Date;

public class Trainee {
	private String firstName;
	private String lastName;
	private int age;
	private Date starting_date;
	private Date finishing_date;
	
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public Date getStarting_date() {
		return starting_date;
	}


	public void setStarting_date(Date starting_date) {
		this.starting_date = starting_date;
	}


	public Date getFinishing_date() {
		return finishing_date;
	}


	public void setFinishing_date(Date finishing_date) {
		this.finishing_date = finishing_date;
	}	
	
}
