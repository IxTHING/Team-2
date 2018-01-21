package com.qa.team2.persistence;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Trainee {
	private Long id;
	private String firstName;
	private String lastName;
	private int age;
	@Temporal(TemporalType.DATE)
	private Date starting_date;
	@Temporal(TemporalType.DATE)
	private Date finishing_date;
	
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
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
