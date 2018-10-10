package com.apap.tutorial5.service;

import java.awt.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial5.model.CarModel;
import com.apap.tutorial5.repository.CarDb;

/**
 * CarServiceImpl
 */
@Service
@Transactional
public class CarServiceImpl implements CarService{
	@Autowired
	private CarDb carDb;
	
	@Override
	public void addCar(CarModel car) {
		carDb.save(car);
	}

	// delete
	@Override
	public void deleteCar(CarModel car) {
		// TODO Auto-generated method stub
		carDb.delete(car);
	}
	
	// update
	@Override
	public void updateCar(Long carIdu, String carBrandu, String carTypeu, Long carPriceu, Integer carAmountu) {
		carDb.getOne(carIdu).setBrand(carBrandu);
		carDb.getOne(carIdu).setType(carTypeu);
		carDb.getOne(carIdu).setPrice(carPriceu);
		carDb.getOne(carIdu).setAmount(carAmountu);		
	}
	
}