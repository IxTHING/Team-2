package com.qa.team2.intergration;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.qa.team2.business.repository.TraineeServiceImpl;

@Path("/trainee")
public class TraineeEndPoint {
	
	@Inject
	private TraineeServiceImpl traineeService;
	
	@GET
	@Path("/xml")
	public String getAllBooks() {
		return traineeService.getAllTrainees();
	}
	
	@POST
	@Path("/xml")
	public String addNewTraineeToMap(String traineeXml) {
		return traineeService.addNewTrainee(traineeXml);
	}
}
