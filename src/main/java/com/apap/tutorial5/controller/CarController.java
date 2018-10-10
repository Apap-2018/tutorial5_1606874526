package com.apap.tutorial5.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.apap.tutorial5.model.*;
import com.apap.tutorial5.service.*;

/**
 * CarController
 */
@Controller
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "dealerId") Long dealerId, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		ArrayList<CarModel> list = new ArrayList<CarModel>();
		list.add(new CarModel());
		dealer.setListCar(list);
		model.addAttribute("dealer", dealer);
		model.addAttribute("pageTitle", "Add Car");
		return "addCar";
	}
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.POST, params= {"simpan"})
	private String addCarSubmit (@ModelAttribute DealerModel dealer, Model model) {
		DealerModel dealern = dealerService.getDealerDetailById(dealer.getId()).get();
		for(CarModel car : dealer.getListCar()) {
			car.setDealer(dealern);
			carService.addCar(car);
		}
		model.addAttribute("pageTitle", "Add Car");
		return "add";
	}
	
	@RequestMapping(value = "/car/add/{dealerId}", params= {"addRow"}, method = RequestMethod.POST)
	private String addRow (@ModelAttribute DealerModel dealer, final BindingResult bindingResult, Model model) {
		dealer.getListCar().add(new CarModel());
		model.addAttribute("dealer", dealer);
		model.addAttribute("pageTitle", "Add Car");
		return "addCar";
	}
	
	@RequestMapping(value="/car/add/{dealerId}", params={"removeRow"}, method=RequestMethod.POST)
	public String removeRow(@ModelAttribute DealerModel dealer, final BindingResult bindingResult, HttpServletRequest req,  Model model) {
		int rowId = Integer.parseInt(req.getParameter("removeRow"));
		dealer.getListCar().remove(rowId);
		model.addAttribute("dealer", dealer);
		model.addAttribute("pageTitle", "Add Car");
		return "addCar";		
	}
	
	// delete
	@RequestMapping(value = "/car/delete", method = RequestMethod.POST)
	private String delete(@ModelAttribute DealerModel dealer, Model model) {	
		for(CarModel car : dealer.getListCar()) {
			carService.deleteCar(car);
		}
		model.addAttribute("pageTitle", "Delete Berhasil");
		return "delete";
	}
	
	// update
	@RequestMapping(value = "/car/update", method = RequestMethod.GET)
	private String updateCarById(@RequestParam("carIdu") Long carIdu, Model model, @RequestParam("carBrandu") String carBrandu, @RequestParam("carTypeu") String carTypeu, @RequestParam("carPriceu") Long carPriceu, @RequestParam("carAmountu") Integer carAmountu) {
		carService.updateCar(carIdu, carBrandu, carTypeu, carPriceu, carAmountu);
		model.addAttribute("pageTitle", "Update Berhasil");
		return "update";
	}
}
