package com.apap.tutorial5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial5.model.CarModel;
import com.apap.tutorial5.model.DealerModel;
import com.apap.tutorial5.service.CarService;
import com.apap.tutorial5.service.DealerService;

/**
 * DealerController
 */
@Controller
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CarService carService;
	
	@RequestMapping("/")
	private String home(Model model) {
		model.addAttribute("pageTitle", "Home");
		return "home";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());

		model.addAttribute("pageTitle", "Add Dealer");
		return "addDealer";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer, Model model) {
		dealerService.addDealer(dealer);
		model.addAttribute("pageTitle", "Add Dealer Berhasil");
		
		return "add";
	}
	
	
	@RequestMapping(value = "/dealer/view")
	public String viewById(@RequestParam("dealerId") Long dealerId, Model model) {
		DealerModel archiveDealer = dealerService.getDealerDetailById(dealerId).get();
		List<CarModel> archiveListCar = archiveDealer.getListCar();
		archiveDealer.setListCar(archiveListCar);
		
		model.addAttribute("dealer", archiveDealer);
		model.addAttribute("pageTitle", "View Dealer");
		return "view-dealer";
	}
	
	// delete
	@RequestMapping(value = "/dealer/delete", method = RequestMethod.GET)
	private String deleteDealerById(@RequestParam("dealerIdd") Long dealerIdd, Model model) {
		dealerService.deleteDealer(dealerIdd);
		model.addAttribute("pageTitle", "Delete Dealer Berhasil");
		return "delete";
	}
	
	// updateDealer(Long dealerIdu, String alamat, String noTelp)
	@RequestMapping(value = "/dealer/update", method = RequestMethod.GET)
	private String updateDealerById(@RequestParam("dealerIdu") Long dealerIdu, Model model, @RequestParam("dealerAlamatu") String dealerAlamatu, @RequestParam("dealerNoTelpu") String dealerNoTelpu) {
		dealerService.updateDealer(dealerIdu, dealerAlamatu, dealerNoTelpu);
		model.addAttribute("pageTitle", "Update");
		return "update";
	}
	
	// view all dealer
	@RequestMapping(value = "/dealer/view-all", method = RequestMethod.GET)
	public String viewAllDealer(Model model) {
		List<DealerModel> listAllDealer = dealerService.viewAllDealer();
		model.addAttribute("listAllDealer", listAllDealer);
		model.addAttribute("pageTitle", "View All Dealers");
		return "view-all-dealer";
	}
}
