package com.qa.team2.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.qa.team2.business.repository.RoomService;

@Entity
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	private Apartment apartment;

	public Room() {

	}

	public Room(Apartment apartment) {
		this.apartment = apartment;
	}
	
	public Room(Long id, Apartment apartment) {
		this(apartment);
		this.id = id;
	}
	
}
