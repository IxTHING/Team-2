package com.qa.team2.intergration;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.team2.business.repository.ApartmentService;

@Path("/apartment")
public class ApartmentEndPoint {

	@Inject
	private ApartmentService apartmentService;

	@GET
	@Path("/json")
	@Produces({ "application/json" })
	public String getAllApartmentAsJson() {
		return apartmentService.getAllApartments();
	}
	
	@GET
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String getApartmentAsJson(@PathParam("id") Long id) {
		return apartmentService.findApartment(id);
	}

	@POST
	@Path("/json")
	@Produces({ "application/json" })
	public String addNewApartment(String apartmentJson) {
		return apartmentService.addNewApartment(apartmentJson);
	}

	@DELETE
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String deleteBookFromBookStore(@PathParam("id") Long id) {
		return apartmentService.deleteApartment(id);
	}
	
	@PUT
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String updateApartment(@PathParam("id") Long id, String apartmentJson) {
		return apartmentService.updateApartment(id, apartmentJson);
	}
	
}
