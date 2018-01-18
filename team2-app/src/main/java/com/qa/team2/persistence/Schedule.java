package com.qa.team2.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column
	@NotNull
	private Date from_date;

	@Temporal(TemporalType.DATE)
	@Column
	@NotNull
	private Date to_date;

	// Table joins for many to many relationships
	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "person_personID"))
	private Person person;

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "room_roomIDs"))
	private Room room;

	public Schedule() {

	}

	public Schedule(Long id, Date from_date, Date to_date, Person person, Room room) {
		super();
		this.id = id;
		this.from_date = from_date;
		this.to_date = to_date;
		this.person = person;
		this.room = room;
	}

	public Schedule(Date from_date, Date to_date, Person person, Room room) {
		super();
		this.from_date = from_date;
		this.to_date = to_date;
		this.person = person;
		this.room = room;
	}
}
