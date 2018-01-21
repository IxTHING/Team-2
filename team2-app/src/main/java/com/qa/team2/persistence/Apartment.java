package com.qa.team2.persistence;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Apartment {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull	
	private String buildingName;
	@NotNull
	private String apartmentNo;
	@NotNull
	private Integer noRooms;
	@NotNull
	@Embedded
	private Address address;
	@NotNull
	private Double deposit;
	
	public Apartment() {
		
	}
	
	public Apartment(String buildingName, String apartmentNo, Integer noRooms, Address address, Double deposit) {
		this.buildingName = buildingName;
		this.apartmentNo = apartmentNo;
		this.noRooms = noRooms;
		this.address = address;
		this.deposit = deposit;
	}

	public Apartment(Long id, String buildingName, String apartmentNo, Integer noRooms, Address address, Double deposit) {
		this(buildingName, apartmentNo, noRooms, address, deposit);
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getApartmentNo() {
		return apartmentNo;
	}

	public void setApartmentNo(String apartmentNo) {
		this.apartmentNo = apartmentNo;
	}
	
	public Integer getNoRooms() {
		return noRooms;
	}

	public void setNoRooms(Integer noRooms) {
		this.noRooms = noRooms;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

}
