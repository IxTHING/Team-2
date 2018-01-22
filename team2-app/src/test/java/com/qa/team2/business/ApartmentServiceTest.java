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

import com.qa.team2.business.repository.ApartmentServiceDBImpl;
import com.qa.team2.business.repository.PersonServiceDBImpl;
import com.qa.team2.persistence.Address;
import com.qa.team2.persistence.Apartment;
import com.qa.team2.persistence.Person;
import com.qa.team2.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class ApartmentServiceTest {

	private String testJsonStringA;
	private String testJsonStringB;
	private Apartment aApartment;
	private Apartment bApartment;
	private Address aAddress;


	@InjectMocks
	private ApartmentServiceDBImpl impl;

	@Mock
	private JSONUtil util;
	private EntityManager em;

	@Before
	public void setUp() {

		impl.setUtil(util);
		em = Mockito.mock(EntityManager.class);
		impl.setEm(em);

		testJsonStringA = "{\"id\": \"1\",\"buildingName\": \"heart\",\"apartmentNo\": \"1121\", \"noRooms\": \"2\", \"address\": \"1\", \"deposit\": \"21.21\"}";
		testJsonStringB = "{\"id\": \"1\",\"buildingName\": \"one\",\"apartmentNo\": \"21\", \"noRooms\": \"2\", \"address\": \"1\", \"deposit\": \"21.21\"}";

		aAddress = new Address("1st street", "North Shields", "Newcastle");
		
		aApartment = new Apartment(1L, "heart", "1121", 2, aAddress, 21.21);
		bApartment = new Apartment(2L, "one", "21", 2, aAddress, 21.21);
	}

	@Test
	public void testCreateApartmentFromString() {
		Mockito.when(util.getObjectForJSON(testJsonStringA, Apartment.class)).thenReturn(aApartment);
		Mockito.doNothing().when(em).persist(aApartment);

		String returnedString = impl.addNewApartment(testJsonStringA);
		Assert.assertEquals(testJsonStringA, returnedString);

		Mockito.verify(util).getObjectForJSON(testJsonStringA, Apartment.class);
	}

	@Test
	public void testUpdateApartmentFromString() {
		Mockito.when(util.getObjectForJSON(testJsonStringA, Apartment.class)).thenReturn(aApartment);
		Mockito.when(util.getObjectForJSON(testJsonStringB, Apartment.class)).thenReturn(bApartment);
		Mockito.when(em.find(Apartment.class, 1L)).thenReturn(aApartment);

		String returnedString = impl.addNewApartment(testJsonStringA);
		Assert.assertEquals(testJsonStringA, returnedString);

		returnedString = impl.updateApartment(2L, testJsonStringB);
		Assert.assertEquals("{\"message\": \"apartment was not updated\"}", returnedString);

		returnedString = impl.updateApartment(1L, testJsonStringB);
		Assert.assertEquals("{\"message\": \"apartment sucessfully updated\"}", returnedString);

	}

	@Test
	public void testDeleteApartment() {
		Mockito.when(util.getObjectForJSON(testJsonStringA, Apartment.class)).thenReturn(aApartment);
		Mockito.doNothing().when(em).remove(aApartment);
		Mockito.when(em.find(Apartment.class, 1L)).thenReturn(aApartment);
		
		impl.addNewApartment(testJsonStringA);
		
		Assert.assertEquals("{\"message\": \"apartment was not removed\"}", impl.deleteApartment(2L));

		String returnedString = impl.deleteApartment(1L);
		Assert.assertEquals("{\"message\": \"apartment sucessfully removed\"}", returnedString);

		Assert.assertNull(impl.findApartment(1L));
		
		
	} 

	@Test
	public void testFindApartment() {
		Mockito.when(util.getObjectForJSON(testJsonStringA, Apartment.class)).thenReturn(aApartment);
		Mockito.when(util.getJSONForObject(aApartment)).thenReturn(testJsonStringA);
		String returnedString = impl.findApartment(1L);
		Assert.assertEquals("{\"message\": \"apartment was not found\"}", returnedString);

		impl.addNewApartment(testJsonStringA);
		Mockito.when(em.find(Apartment.class, 1L)).thenReturn(aApartment);
		Assert.assertEquals(impl.findApartment(1L), testJsonStringA);

	}
	
}
