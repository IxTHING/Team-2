package com.qa.team2.business.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.team2.persistence.Room;
import com.qa.team2.util.JSONUtil;

@Default
@Transactional(SUPPORTS)
public class RoomServiceDBImpl implements RoomService {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Inject
	private JSONUtil util;

	@Override
	public String getAllRooms() {
		Query query = em.createQuery("SELECT r FROM Room r");
		Collection<Room> rooms = (Collection<Room>) query.getResultList();
		return util.getJSONForObject(rooms);
	}

	@Override
	public String findRoom(Long id) {
		Room aRoom = em.find(Room.class, id);
		if (aRoom != null) {
			return util.getJSONForObject(aRoom);
		}
		return "{\"message\": \"room not found - room to find was null\"}";

	}

	@Override
	@Transactional(REQUIRED)
	public String createRoom(String JSONRoom) {
		Room aRoom = util.getObjectForJSON(JSONRoom, Room.class);
		if (aRoom != null) {
			em.persist(aRoom);
			return "{\"message\": \"room successfully added\"}";
		}
		return "{\"message\": \"room not created - room to create was null\"}";

	}

	@Override
	@Transactional(REQUIRED)
	public String updateRoom(Long id, String JSONNewDetails) {
		Room inRoom = util.getObjectForJSON(JSONNewDetails, Room.class);
		Room updateRoom = em.find(Room.class, id);
		if (updateRoom != null) {
			updateRoom = inRoom;
			em.merge(updateRoom);
			return "{\"message\": \"room successfully updated\"}";
		}
		return "{\"message\": \"room not found - room to update was null\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteRoom(Long id) {
		Room aRoom = em.find(Room.class, id);
		if (aRoom != null) {
			em.remove(aRoom);
			return "{\"message\": \"room successfully deleted\"}";
		}
		return "{\"message\": \"room not found - room to delete was null\"}";
	}
}
