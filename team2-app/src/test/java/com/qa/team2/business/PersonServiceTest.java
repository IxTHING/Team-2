package com.qa.team2.business;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.team2.business.repository.PersonServiceDBImpl;
import com.qa.team2.persistence.Person;
import com.qa.team2.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

	private String testJsonStringA;
	private String testJsonStringB;
	private Person aPerson;
	private Person bPerson;

	@InjectMocks
	private PersonServiceDBImpl impl;

	@Mock
	private JSONUtil util;
	private EntityManager em;

	@Before
	public void setUp() {

		impl.setUtil(util);
		em = Mockito.mock(EntityManager.class);
		impl.setEm(em);

		testJsonStringA = "{\"personID\": \"1\",\"first_name\": \"James\",\"last_name\": \"Herbert\",\"email\": \"test@test.com\", \"phone_number\": \"01234567891\"}";
		testJsonStringB = "{\"personID\": \"1\",\"first_name\": \"Jenkins\",\"last_name\": \"Harlot\",\"email\": \"test2@test.com\", \"phone_number\": \"01234567891\"}";

		aPerson = new Person(1L, "James", "Herbert", "test@test.com", "01234567891");
		bPerson = new Person(2L, "Jenkins", "Harlot", "test2@test.com", "01234567891");
	}

	@Test
	public void testCreatePersonFromString() {
		Mockito.when(util.getObjectForJSON(testJsonStringA, Person.class)).thenReturn(aPerson);
		Mockito.doNothing().when(em).persist(aPerson);

		String returnedString = impl.addNewPerson(testJsonStringA);
		Assert.assertEquals(testJsonStringA, returnedString);

		Mockito.verify(util).getObjectForJSON(testJsonStringA, Person.class);
	}

	@Test
	public void testUpdatePersonFromString() {
		Mockito.when(util.getObjectForJSON(testJsonStringA, Person.class)).thenReturn(aPerson);
		Mockito.when(util.getObjectForJSON(testJsonStringB, Person.class)).thenReturn(bPerson);
		Mockito.when(em.find(Person.class, 1L)).thenReturn(aPerson);

		String returnedString = impl.addNewPerson(testJsonStringA);
		Assert.assertEquals(testJsonStringA, returnedString);

		returnedString = impl.updatePerson(2L, testJsonStringB);
		Assert.assertEquals("{\"message\": \"person was not updated\"}", returnedString);

		returnedString = impl.updatePerson(1L, testJsonStringB);
		Assert.assertEquals("{\"message\": \"person sucessfully updated\"}", returnedString);

	}

	@Test
	public void testDeletePerson() {
		Mockito.when(util.getObjectForJSON(testJsonStringA, Person.class)).thenReturn(aPerson);
		Mockito.doNothing().when(em).remove(aPerson);
		Mockito.when(em.find(Person.class, 1L)).thenReturn(aPerson);
		
		impl.addNewPerson(testJsonStringA);
		
		Assert.assertEquals("{\"message\": \"person was not removed\"}", impl.deletePerson(2L));

		String returnedString = impl.deletePerson(1L);
		Assert.assertEquals("{\"message\": \"person sucessfully removed\"}", returnedString);

		Assert.assertNull(impl.findPerson(1L));
		
		
	} 

	@Test
	public void testFindPerson() {
		Mockito.when(util.getObjectForJSON(testJsonStringA, Person.class)).thenReturn(aPerson);
		Mockito.when(util.getJSONForObject(aPerson)).thenReturn(testJsonStringA);
		String returnedString = impl.findPerson(1L);
		Assert.assertEquals("{\"message\": \"person was not found\"}", returnedString);

		impl.addNewPerson(testJsonStringA);
		Mockito.when(em.find(Person.class, 1L)).thenReturn(aPerson);
		Assert.assertEquals(impl.findPerson(1L), testJsonStringA);

	}

}
