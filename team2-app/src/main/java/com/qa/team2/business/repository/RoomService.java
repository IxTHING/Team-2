package com.qa.team2.business.repository;

public interface RoomService {
	
	String getAllRooms();
	
	String findRoom(Long id);
	
	String createRoom(String JSONRoom);
	
	String updateRoom(Long id, String JSONNewDetails);
	
	String deleteRoom(Long id);

}
