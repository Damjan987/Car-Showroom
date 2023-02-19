package com.luv2code.springsecurity.demo.autosalon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springsecurity.demo.entity.*;
import com.luv2code.springsecurity.demo.autosalon.service.*;

@Controller
@RequestMapping("/brand")
public class BrandController {
	@Autowired
	private BrandService brandService; 
	
	@Autowired
	private CarService carService;
	
	@GetMapping("/list")
	public String listBrands(Model theModel) {
		List<Brand> theBrands = brandService.getBrands();
		theModel.addAttribute("brands", theBrands);
		return "list-brands";
	}
	
	@GetMapping("/brandForm")
	public String createBrand(Model theModel) {
		Brand theBrand = new Brand();
		theModel.addAttribute("brand", theBrand);
		return "brand-form";
	}
	
	@PostMapping("/saveBrand")
	public String saveBrand(@ModelAttribute("brand") Brand theBrand) {
		brandService.saveBrand(theBrand);
		return "redirect:/brand/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("brandId") int theId, Model theModel) {
		Brand theBrand = brandService.getBrand(theId);
		theModel.addAttribute("brand", theBrand);
		return "brand-form";
	}
	
	@GetMapping("/delete")
	public String deleteBrand(@RequestParam("brandId") int theId) {
		Brand theBrand = brandService.getBrand(theId);
		for (Car car : theBrand.getCars()) {
			carService.deleteCar(car.getId());
		}	
		brandService.deleteBrand(theId);
		return "redirect:/brand/list";
	}
}