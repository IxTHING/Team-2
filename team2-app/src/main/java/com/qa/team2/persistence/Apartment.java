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
	private String address;
	@NotNull
	private Double deposit;
	
	public Apartment() {
		
	}
	
	public Apartment(String buildingName, String apartmentNo, Integer noRooms, String address, Double deposit) {
		this.buildingName = buildingName;
		this.apartmentNo = apartmentNo;
		this.noRooms = noRooms;
		this.address = address;
		this.deposit = deposit;
	}

	public Apartment(Long id, String buildingName, String apartmentNo, Integer noRooms, String address, Double deposit) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

}
