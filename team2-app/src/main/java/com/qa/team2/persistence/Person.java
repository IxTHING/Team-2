package com.qa.team2.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
public class Person {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) @Column(name = "id")
	private Long personID;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@Email 
	@NotNull
	private String email;
	@NotNull
	private String phoneNumber;
	
	public Person() {
		
	}
	
	public Person(String firstName, String lastName, String email, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public Person(Long personID, String firstName, String lastName, String email, String phoneNumber) {
		this(firstName, lastName, email, phoneNumber);
		this.personID = personID;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getPersonID() {
		return personID;
	}

}