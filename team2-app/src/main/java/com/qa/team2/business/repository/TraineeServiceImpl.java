package com.qa.team2.business.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

import com.qa.team2.persistence.Trainee;

@WebService(endpointInterface = "com.qa.team2.business.repository.TraineeService") 
public class TraineeServiceImpl implements TraineeService {

	private static Map<Long,Trainee> trainees = new HashMap<Long,Trainee>();
	
	@Override
	public Trainee[] getAllTrainees() {
		System.out.println("gettAll");
		Set<Long> ids = trainees.keySet();
		Trainee[] t = new Trainee[ids.size()];
		int i=0;
		for(Long id : ids){
			t[i] = trainees.get(id);
			i++;
		}
		return t;
	}

	@Override
	public boolean addNewTrainee(Trainee t) {
		System.out.println("addNew");
		if(trainees.get(t.getId()) != null) return false;
			trainees.put(t.getId(), t);
			return true;
	}

	@Override
	public boolean deleteTrainee(Long id) {
		System.out.println("deleteAll");
		if(trainees.get(id) == null) return false;
		trainees.remove(id);
		return true;
	}

	@Override
	public Trainee findTrainee(Long id) {
		System.out.println("findTrainee");
		return trainees.get(id);
	}
}
