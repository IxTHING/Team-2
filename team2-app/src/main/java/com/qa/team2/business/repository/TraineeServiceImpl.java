package com.qa.team2.business.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.qa.team2.persistence.Trainee;
import com.qa.team2.util.XMLUtil;

@Default
@Transactional(SUPPORTS)
@ApplicationScoped
public class TraineeServiceImpl {

	private Map<Long, Trainee> traineeMap;
	private Long ID;
	
	@Inject
	private XMLUtil util;
	
	public TraineeServiceImpl() {
		this.traineeMap = new HashMap<Long, Trainee>();
		ID = 0L;
	}
	
	public String getAllTrainees() {
		return util.getXMLForObject(traineeMap);
	}
	
	public String findTrainee(Long id) {
		Trainee aTrainee = traineeMap.get(id);
		return util.getXMLForObject(aTrainee);
	}
	
	@Transactional(REQUIRED)
	public String createTrainee(String traineeXML) {
		Trainee newTrainee = (Trainee) util.getObjectForXML(traineeXML);
		if (newTrainee != null) {
			traineeMap.put(ID, newTrainee);
			ID++;
			return "{\"message\": \"trainee successfully added\"}";
		}
		return "{\"message\": \"trainee not created - trainee to create was null\"}";
	}
	
	@Transactional(REQUIRED)
	public String updateTrainee(Long id, String traineeXML) {
		Trainee aTrainee = traineeMap.get(id);
		Trainee newTrainee = (Trainee) util.getObjectForXML(traineeXML);
		if (aTrainee != null) {
			traineeMap.put(id, newTrainee);
			return "{\"message\": \"trainee successfully updated\"}";
		}
		return "{\"message\": \"trainee not updated - trainee at that id is null\"}";
	}
	
	@Transactional(REQUIRED)
	public String deleteTrainee(Long id) {
		Trainee aTrainee = traineeMap.get(id);
		if (aTrainee != null) {
			traineeMap.remove(id);
			return "{\"message\": \"trainee successfully deleted\"}";
		}
		return "{\"message\": \"trainee not deleted - trainee to delete is null\"}";
	}
	
}
