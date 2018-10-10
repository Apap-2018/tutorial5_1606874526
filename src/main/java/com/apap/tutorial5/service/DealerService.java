package com.apap.tutorial5.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial5.model.DealerModel;

/**
 * DealerService
 */
public interface DealerService {
	Optional<DealerModel> getDealerDetailById(Long id);
	
	void addDealer(DealerModel dealer);
	// delete
	void deleteDealer(Long dealerIdd);
	//update
	void updateDealer(Long dealerIdu, String dealerAlamatu, String dealerBoTelpu);
	// view all
	List<DealerModel> viewAllDealer();
}