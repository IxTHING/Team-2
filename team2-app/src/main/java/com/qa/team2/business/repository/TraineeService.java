package com.qa.team2.business.repository;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.qa.team2.persistence.Trainee;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface TraineeService {
	
	@WebMethod
	Trainee[] getAllTrainees();

	@WebMethod
	boolean addNewTrainee(Trainee t);

	@WebMethod
	boolean deleteTrainee(Long id);
	
	@WebMethod
	Trainee findTrainee(Long id);
	
	@WebMethod
	boolean sendTraineeMule(Trainee t);
}
