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

import com.qa.team2.persistence.Schedule;
import com.qa.team2.util.JSONUtil;

@Default
@Transactional(SUPPORTS)
public class ScheduleServiceDBImpl implements ScheduleService {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Inject
	private JSONUtil util;

	public String findSchedule(Long id) {
		if(em.find(Schedule.class, id) != null) {
			return util.getJSONForObject(em.find(Schedule.class, id));
		}
		else {
			return "{\"message\": \"schedule not found\"}";
		}
		
	}

	public String getAllSchedules() {
		Query findAll = em.createQuery("SELECT s FROM Schedule s");
		Collection<Schedule> schedules = (Collection<Schedule>) findAll.getResultList();
		return util.getJSONForObject(schedules);
	}

	@Transactional(REQUIRED)
	public String createScheduleFromString(String schedule) {
		Schedule newSchedule = util.getObjectForJSON(schedule, Schedule.class);
		em.persist(newSchedule);
		return util.getJSONForObject(newSchedule);
	}
	
	@Transactional(REQUIRED)
	public String deleteSchedule(Long id) {
		Schedule scheduleToDelete = em.find(Schedule.class, id);
		if (scheduleToDelete != null) {
			em.remove(scheduleToDelete);
			return "{\"message\": \"schedule sucessfully removed\"}";
		}
		else {
			 return "{\"message\": \"schedule not removed, error occurred, most likely null object\"}";
		}
	}
	
	@Transactional(REQUIRED)
	public String updateSchedule(Long id, String schedule) {
		Schedule scheduleToUpdate = em.find(Schedule.class, id);
		Schedule newSchedule = util.getObjectForJSON(schedule, Schedule.class);
		if (scheduleToUpdate != null) {
			scheduleToUpdate = newSchedule;
			em.merge(scheduleToUpdate);
			return "{\"message\": \"schedule sucessfully updated\"}";
		}
		else {
			 return "{\"message\": \"schedule not updated, error occurred, most likely null object\"}";
		}
	}
}
