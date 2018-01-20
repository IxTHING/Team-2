package com.qa.team2.intergration;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.qa.team2.business.repository.TraineeServiceImpl;

@Path("/trainee")
public class TraineeEndPoint {
	
	@Inject
	private TraineeServiceImpl traineeService;
	
	@GET
	@Path("/xml")
	public String getAllTrainees() {
		return traineeService.getAllTrainees();
	}
	
	
	@GET
	@Path("/xml/{id}")
	public String findTrainee(@PathParam("id") Long id) {
		return null;
	}
	
	@POST
	@Path("/xml")
	public String createTrainee(String traineeXml) {
		return null;
	}
	
	@PUT
	@Path("/xml/{id}")
	public String updateTraineeOnMap(@PathParam("id") Long id, String traineeXml){
		return null;
	}
	
	@DELETE
	@Path("/xml/{id}")
	public String deleteTraineeFromMap(@PathParam("id") Long id){
		return null;
	}
}
