package com.qa.team2.business;

import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.team2.business.repository.ScheduleServiceDBImpl;
import com.qa.team2.persistence.Person;
import com.qa.team2.persistence.Room;
import com.qa.team2.persistence.Schedule;
import com.qa.team2.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTest {
	private String testJsonStringA;
	private String testJsonStringB;
	private Schedule aSchedule;
	private Schedule bSchedule;
	private Person aPerson;
	private Person bPerson;
	private Room aRoom;
	private Date dateFrom;
	private Date dateTo;

	@InjectMocks
	private ScheduleServiceDBImpl impl;

	@Mock
	private JSONUtil util;
	private EntityManager em;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {

		impl.setUtil(util);
		em = Mockito.mock(EntityManager.class);
		impl.setEm(em);

		testJsonStringA = "{\"id\": \"1\",\"from_date\": \"2003, 12, 02\",\"to_date\": \"2005,03, 10\",\"person\": \"1\", \"room\": \"1\"}";
		testJsonStringB = "{\"id\": \"1\",\"from_date\": \"2003, 12, 02\",\"to_date\": \"2005,03, 10\",\"person\": \"2\", \"room\": \"1\"}";

		dateFrom = new Date(2003, 12, 02);
		dateTo = new Date(2005,03, 10);
		
		aSchedule = new Schedule(1L, dateFrom, dateTo, aPerson, aRoom);
		aSchedule = new Schedule(1L, dateFrom, dateTo, bPerson, aRoom);
		
		aPerson = new Person(1L, "James", "Herbert", "test@test.com", "01234567891");
		bPerson = new Person(2L, "Jenkins", "Harlot", "test2@test.com", "01234567891");
		
		aRoom = new Room(1L);
	}

	@Test
	public void testCreateScheduleFromString() {
		Mockito.when(util.getObjectForJSON(testJsonStringA, Schedule.class)).thenReturn(aSchedule);
		Mockito.doNothing().when(em).persist(aSchedule);
		Mockito.when(util.getJSONForObject(aSchedule)).thenReturn(testJsonStringA);

		String returnedString = impl.createScheduleFromString(testJsonStringA);
		Assert.assertEquals(testJsonStringA, returnedString);

		Mockito.verify(util).getObjectForJSON(testJsonStringA, Schedule.class);
	}

	@Test
	public void testUpdateScheduleFromString() {
		Mockito.when(util.getObjectForJSON(testJsonStringA, Schedule.class)).thenReturn(aSchedule);
		Mockito.when(util.getObjectForJSON(testJsonStringB, Schedule.class)).thenReturn(bSchedule);
		Mockito.when(em.find(Schedule.class, 1L)).thenReturn(aSchedule);
		Mockito.when(util.getJSONForObject(aSchedule)).thenReturn(testJsonStringA);

		String returnedString = impl.createScheduleFromString(testJsonStringA);
		Assert.assertEquals(testJsonStringA, returnedString);

		returnedString = impl.updateSchedule(2L, testJsonStringB);
		Assert.assertEquals("{\"message\": \"schedule not updated, error occurred, most likely null object\"}", returnedString);

		returnedString = impl.updateSchedule(1L, testJsonStringB);
		Assert.assertEquals("{\"message\": \"schedule sucessfully updated\"}", returnedString);

	}

	@Test
	public void testDeleteSchedule() {
		Mockito.when(util.getObjectForJSON(testJsonStringA, Schedule.class)).thenReturn(aSchedule);
		Mockito.doNothing().when(em).remove(aSchedule);
		Mockito.when(em.find(Schedule.class, 1L)).thenReturn(aSchedule);
		
		impl.createScheduleFromString(testJsonStringA);
		
		Assert.assertEquals("{\"message\": \"schedule not removed, error occurred, most likely null object\"}", impl.deleteSchedule(2L));

		String returnedString = impl.deleteSchedule(1L);
		Assert.assertEquals("{\"message\": \"schedule sucessfully removed\"}", returnedString);

		Assert.assertNull(impl.findSchedule(1L));
		
		
	} 

	@Test
	public void testFindSchedule() {
		Mockito.when(util.getObjectForJSON(testJsonStringA, Schedule.class)).thenReturn(aSchedule);
		Mockito.when(util.getJSONForObject(aSchedule)).thenReturn(testJsonStringA);
		String returnedString = impl.findSchedule(1L);
		Assert.assertEquals("{\"message\": \"schedule not found\"}", returnedString);

		impl.createScheduleFromString(testJsonStringA);
		Mockito.when(em.find(Schedule.class, 1L)).thenReturn(aSchedule);
		Assert.assertEquals(impl.findSchedule(1L), testJsonStringA);

	}
}
