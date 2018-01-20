package com.qa.team2.business.repository;

import javax.jws.WebService;

@WebService(endpointInterface = "com.qa.team2.business.repository.TraineeService") 
public class TraineeServiceImpl implements TraineeService {

	@Override
	public String getAllTrainees() {
		return "Hello Trainee";
	}

	@Override
	public String addNewTrainee(String bookJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteTrainee(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findTrainee(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateTrainee(Long id, String apartment) {
		// TODO Auto-generated method stub
		return null;
	}	
}
