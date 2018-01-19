package com.qa.team2.business.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.transaction.Transactional;

import com.qa.team2.persistence.Trainee;
import com.qa.team2.util.XMLUtil;

@Default
@Transactional(SUPPORTS)
@ApplicationScoped
public class TraineeServiceImpl {

	private Map<Integer, Trainee> traineeMap;
	private int ID;
	
	XMLUtil util = new XMLUtil();
	
	public TraineeServiceImpl() {
		this.traineeMap = new HashMap<Integer, Trainee>();
		ID = 0;
	}
	
	public String getAllTrainees() {
		return util.getXMLForObject(traineeMap);
	}
	
	@Transactional(REQUIRED)
	public String addNewTrainee(String traineeXML) {
		Trainee newTrainee = (Trainee) util.getObjectForXML(traineeXML);
		traineeMap.put(ID, newTrainee);
		return newTrainee.getName();
	}
	
}
