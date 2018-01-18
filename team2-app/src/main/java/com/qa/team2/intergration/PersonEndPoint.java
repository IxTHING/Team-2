package com.qa.team2.intergration;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.team2.business.repository.PersonService;

@Path("/person")
public class PersonEndPoint {

	@Inject
	private PersonService personService;

	@GET
	@Path("/json")
	@Produces({ "application/json" })
	public String getAllPersonsAsJson() {
		return personService.getAllPersons();
	}
	
	@GET
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String getPersonAsJson(@PathParam("id") Long id) {
		return personService.findPerson(id);
	}

	@POST
	@Path("/json")
	@Produces({ "application/json" })
	public String addNewPerson(String personJson) {
		return personService.addNewPerson(personJson);
	}

	@DELETE
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String deleteBookFromBookStore(@PathParam("id") Long id) {
		return personService.deletePerson(id);
	}
	
	@PUT
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String updatePerson(@PathParam("id") Long id, String personJson) {
		return personService.updatePerson(id, personJson);
	}
	
}
