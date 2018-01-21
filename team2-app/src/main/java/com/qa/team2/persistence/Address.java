package com.qa.team2.persistence;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String number;
	private String name;
	private String street;
	private String town;
	private String city;
	private String county;
	private String postcode;
	
	public Address() {
		super();
	}
	
	public Address(String street, String town, String city) {
		this.street = street;
		this.town = town;
		this.city = city;
	}
	public Address(String number, String name, String street, String town, String city, String county,
			String postcode) {
		this(street, town, city);
		this.number = number;
		this.name = name;
		this.city = city;
		this.postcode = postcode;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	
}
