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

import com.qa.team2.persistence.Person;
import com.qa.team2.util.JSONUtil;

@Default
@Transactional(SUPPORTS)
public class PersonServiceDBImpl implements PersonService{

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Inject
	private JSONUtil util;


	public String getAllPersons() {
		Query query = em.createQuery("SELECT e FROM Person e");
		Collection<Person> persons = (Collection<Person>) query.getResultList();
		return util.getJSONForObject(persons);
	}
	
	public String findPerson(Long personId) {
		Person person = getPerson(new Long(personId));
		if (person != null) {
			return util.getJSONForObject(em.find(Person.class, personId));
		}
		return "{\"message\": \"person was not found\"}";
	}
	
	@Transactional(REQUIRED)
	public String updatePerson(Long personId, String personValue) {
		Person newPerson = util.getObjectForJSON(personValue, Person.class);
		Person personInDB = getPerson(personId);
		if (personInDB != null) {
			personInDB = newPerson;
			em.merge(personInDB);
			return "{\"message\": \"person sucessfully updated\"}";
		}
		return "{\"message\": \"person was not updated\"}";
	}


	@Transactional(REQUIRED)
	public String addNewPerson(String personJson) {
		Person newPerson = util.getObjectForJSON(personJson, Person.class);
		em.persist(newPerson);
		return personJson;
	}


	@Transactional(REQUIRED)
	public String deletePerson(Long personId) {
		Person person = getPerson(new Long(personId));
		if (person != null) {
			em.remove(person);
			return "{\"message\": \"person sucessfully removed\"}";
		}
		return "{\"message\": \"person was not removed\"}";
	}

	
	private Person getPerson(Long id) {
		return em.find(Person.class, id);
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
