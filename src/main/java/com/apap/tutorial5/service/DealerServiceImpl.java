package com.apap.tutorial5.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial5.model.DealerModel;
import com.apap.tutorial5.repository.DealerDb;

/**
 * DealerServiceImpl
 */
@Service
@Transactional
public class DealerServiceImpl implements DealerService {
	@Autowired
	private DealerDb dealerDb;
	
	@Override
	public Optional<DealerModel> getDealerDetailById(Long id) {
		return dealerDb.findById(id);
	}
	
	@Override
	public void addDealer(DealerModel dealer) {
		dealerDb.save(dealer);
	}
	
	// delete
	@Override
	public void deleteDealer(Long dealerIdd) {
		dealerDb.deleteById(dealerIdd);
	}

	// update
	public void updateDealer(Long dealerIdu, String alamat, String noTelp) {
		dealerDb.getOne(dealerIdu).setAlamat(alamat);
		dealerDb.getOne(dealerIdu).setNoTelp(noTelp);		
	}

	// view all dealer
	@Override
	public List<DealerModel> viewAllDealer() {
		return dealerDb.findAll();		
	}

}