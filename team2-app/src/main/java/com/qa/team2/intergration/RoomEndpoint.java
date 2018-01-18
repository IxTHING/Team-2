package com.qa.team2.intergration;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.team2.business.repository.RoomService;

@Path("/room")
public class RoomEndpoint {

	@Inject
	private RoomService roomService;

	@GET
	@Path("/json")
	@Produces({ "application/json" })
	public String getRoomsAsJson() {
		return roomService.getAllRooms();
	}
	
	@GET
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String getRoom(@PathParam("id") Long id) {
		return roomService.findRoom(id);
	}

	@POST
	@Path("/json")
	@Produces({ "application/json" })
	public String createRoom(String roomJson) {
		return roomService.createRoom(roomJson);
	}

	@DELETE
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String deleteRoom(@PathParam("id") Long id) {
		return roomService.deleteRoom(id);
	}
	
	@PUT
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String updateRoom(@PathParam("id") Long id, String roomJson) {
		return roomService.updateRoom(id, roomJson);
	}

}
