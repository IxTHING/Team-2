package com.qa.team2.intergration;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.qa.team2.business.repository.ScheduleService;

@Path("/schedule")
public class ScheduleEndpoint {

		@Inject
		private ScheduleService scheduleService;

		@GET
		@Path("/json")
		@Produces({ "application/json" })
		public String getSchedules() {
			return scheduleService.getAllSchedules();
		}
		
		@GET
		@Path("/json/{id}")
		@Produces({ "application/json" })
		public String getSchedules(@PathParam("id") Long id) {
			return scheduleService.findSchedule(id);
		}

		@POST
		@Path("/json")
		@Produces({ "application/json" })
		public String addNewSchedule(String schedule) {
			return scheduleService.createScheduleFromString(schedule);
		}

		@DELETE
		@Path("/json/{id}")
		@Produces({ "application/json" })
		public String deleteSchedule(@PathParam("id") Long id) {
			return scheduleService.deleteSchedule(id);
		}
		
		@PUT
		@Path("/json/{id}")
		@Produces({"application/json"})
		public String updateSchedule(@PathParam("id")Long id, String schedule) {
			return scheduleService.updateSchedule(id, schedule);
		}

	}
