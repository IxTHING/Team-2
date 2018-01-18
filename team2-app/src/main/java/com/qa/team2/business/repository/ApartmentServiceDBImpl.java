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

import com.qa.team2.persistence.Apartment;
import com.qa.team2.util.JSONUtil;

@Default
@Transactional(SUPPORTS)
public class ApartmentServiceDBImpl implements ApartmentService {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Inject
	private JSONUtil util;


	public String getAllApartments() {
		Query query = em.createQuery("SELECT e FROM Apartment e");
		Collection<Apartment> apartments = (Collection<Apartment>) query.getResultList();
		return util.getJSONForObject(apartments);
	}
	
	public String findApartment(Long apartmentId) {
		Apartment apartment = getApartment(new Long(apartmentId));
		if (apartment != null) {
			return util.getJSONForObject(em.find(Apartment.class, apartmentId));
		}
		return "{\"message\": \"apartment was not found\"}";
	}
	
	@Transactional(REQUIRED)
	public String updateApartment(Long apartmentId, String apartmentValue) {
		Apartment newAparment = util.getObjectForJSON(apartmentValue, Apartment.class);
		Apartment apartmentInDB = getApartment(apartmentId);
		if (apartmentInDB != null) {
			apartmentInDB = newAparment;
			em.merge(apartmentInDB);
			return "{\"message\": \"apartment sucessfully updated\"}";
		}
		return "{\"message\": \"apartment was not updated\"}";
	}


	@Transactional(REQUIRED)
	public String addNewApartment(String apartmentJson) {
		Apartment newApartment = util.getObjectForJSON(apartmentJson, Apartment.class);
		em.persist(newApartment);
		return apartmentJson;
	}


	@Transactional(REQUIRED)
	public String deleteApartment(Long apartmentId) {
		Apartment apartment = getApartment(new Long(apartmentId));
		if (apartment != null) {
			em.remove(apartment);
			return "{\"message\": \"apartment sucessfully removed\"}";
		}
		return "{\"message\": \"apartment was not removed\"}";
	}

	
	private Apartment getApartment(Long id) {
		return em.find(Apartment.class, id);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public JSONUtil getUtil() {
		return util;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}
