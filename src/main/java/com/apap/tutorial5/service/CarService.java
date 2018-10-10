package com.apap.tutorial5.service;

import java.awt.List;

import com.apap.tutorial5.model.CarModel;

/**
 * CarService
 */
public interface CarService {
	void addCar(CarModel car);
	// delete
	void deleteCar(CarModel car);
	//update
	void updateCar(Long carIdu, String carBrandu, String carTypeu, Long carPriceu, Integer carAmountu);	
}