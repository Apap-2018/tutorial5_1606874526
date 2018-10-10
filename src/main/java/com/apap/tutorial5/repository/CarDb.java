package com.apap.tutorial5.repository;

import java.awt.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial5.model.CarModel;

/**
 * CarDb
 */
@Repository
public interface CarDb extends JpaRepository<CarModel, Long> {
	CarModel findByType(String type);
}