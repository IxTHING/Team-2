package com.qa.team2.persistence;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.qa.team2.business.repository.ApartmentService;
import com.qa.team2.business.repository.RoomService;
import com.qa.team2.util.JSONUtil;

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

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_room_apartment"))
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
