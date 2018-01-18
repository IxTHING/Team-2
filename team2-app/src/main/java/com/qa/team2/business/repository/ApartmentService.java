package com.qa.team2.business.repository;

public interface ApartmentService {

	String getAllApartments();

	String addNewApartment(String bookJson);

	String deleteApartment(Long id);
	
	String findApartment(Long id);
	
	String updateApartment(Long id, String apartment);
}
