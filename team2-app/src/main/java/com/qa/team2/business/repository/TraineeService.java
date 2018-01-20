package com.qa.team2.business.repository;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface TraineeService {
	
	@WebMethod
	String getAllTrainees();

	@WebMethod
	String addNewTrainee(String trainee);

	@WebMethod
	String deleteTrainee(Long id);
	
	@WebMethod
	String findTrainee(Long id);
	
	@WebMethod
	String updateTrainee(Long id, String trainee);
}
