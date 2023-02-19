package com.luv2code.springsecurity.demo.autosalon.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springsecurity.demo.service.UserService;
import com.luv2code.springsecurity.demo.autosalon.service.*;
import com.luv2code.springsecurity.demo.dao.UserDao;
import com.luv2code.springsecurity.demo.entity.*;


@Controller
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserService userService;
		
	private List<Car> carsToDelete = new ArrayList<Car>();
	private List<Car> tempCars = null;
	
	@GetMapping("/list")
	public String listCars(Model theModel) {
		List<Car> theCars = carService.getCars();
		List<Integer> carIndexes = new ArrayList<Integer>();
		ID carId = new ID();
		theModel.addAttribute("carId", carId);
		theModel.addAttribute("carsToDelete", carsToDelete);
		theModel.addAttribute("cars", theCars);
		
		int flag = 0;
		for(int i = 1; i < theCars.size(); i++) {
			for(int j = 0; j < carsToDelete.size(); j++) {
				if(theCars.get(i).getId() == carsToDelete.get(j).getId()) {
					carIndexes.add(1);
					flag = 1;
				}
			}
			if(flag == 0) {
				carIndexes.add(0);
			}
			flag = 0;
		}
		theModel.addAttribute("carIndexes", carIndexes);
		return "list-cars";
	}
	
	@GetMapping("/carForm")
	public String createCar(Model theModel) {
		Car theCar = new Car();
		theModel.addAttribute("car", theCar);
		List<Brand> theBrands = brandService.getBrands();
		theModel.addAttribute("brands", theBrands);
		return "car-form";
	}
	
	@PostMapping("/addSelectedCar")
	public String multipleDeletion(@ModelAttribute("carId") ID carId) {
		Car theCar = carService.getCar(Integer.parseInt(carId.getId()+""));
		carsToDelete.add(theCar);
		return "redirect:/car/list";
	}
	
	@GetMapping("/deleteSelectedCars")
	public String deleteSelectedCars() {
		for(int i = 0; i < carsToDelete.size(); ++i) {
			carService.deleteCar(carsToDelete.get(i).getId());
		}
		carsToDelete.clear();
		return "redirect:/car/list";
	}

	@PostMapping("/saveCar1")
	public String saveCar1(@RequestParam("file") String file, @RequestParam("model") String name, 
			@RequestParam("brand") int theBrand, @RequestParam("price") int price) {
		
		Brand brand = brandService.getBrand(theBrand);
		carService.saveCar1(file, name, brand, price);
		return "redirect:/car/list";
	}
	
	@PostMapping("/updateCar")
	public String updateCar(@ModelAttribute("car") Car theCar, @ModelAttribute("brand") int theBrand, @ModelAttribute("file") String file) {
		Brand brand = brandService.getBrand(theBrand);
		theCar.setImage(file);
		theCar.setBrandId(brand);
		carService.saveCar(theCar);
		return "redirect:/car/list";
	}
	
	@PostMapping("/updateUserId")
	public String updateUserId(@ModelAttribute("carId") int theId, @ModelAttribute("uid") int uid) {
		Car car = carService.getCar(theId);
		User user = userDao.getUser(uid);
		car.setUserId(user);
		carService.saveCar(car);
		return "redirect:/car/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("carId") int theId, Model theModel) {
		Car theCar = carService.getCar(theId);
		theModel.addAttribute("car", theCar);
		List<Brand> theBrands = brandService.getBrands();
		theModel.addAttribute("brands", theBrands);
		return "update-car-form";
	}
	
	@GetMapping("/delete")
	public String deleteCar(@RequestParam("carId") int theId, Authentication auth) throws UsernameNotFoundException {
		User userToSet = userService.getUser(userService.loadUserId(auth.getName().toString()));
		Car theCar = carService.getCar(theId);
		theCar.setUserId(userToSet);
		carService.saveCar(theCar);
		return "redirect:/car/list";
	}
	
	
	@GetMapping("/basket")
	public String showMyBasket(Authentication auth, Model theModel) throws UsernameNotFoundException {
		User user = userService.getUser(userService.loadUserId(auth.getName().toString()));
		List<Car> cars = carService.getCars();
		List<Car> userCars = new ArrayList<Car>();
		double totalPrice = 0;
		
		for(int i = 0; i < cars.size(); i++) {	
			if(cars.get(i).getUserId() == null) {
				continue;
			}
			if(cars.get(i).getUserId().getId() == user.getId()) {
				totalPrice += cars.get(i).getPrice();
				userCars.add(cars.get(i));
			}
		}
		tempCars = userCars;
		theModel.addAttribute("totalPrice", totalPrice);
		theModel.addAttribute("userCars", userCars);
		return "basket";
	}
	
	@GetMapping("/deleteUserCars")
	public String deleteUserCars() {
		for(int i = 0; i < tempCars.size(); i++) {	
			carService.deleteCar(tempCars.get(i).getId());
		}
		return "redirect:/car/list";
	}
	
	@GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName, Model theModel) {
        List<Car> theCars = carService.searchCars(theSearchName);
        theModel.addAttribute("cars", theCars);
        return "list-cars";
    }
	
	@GetMapping("/filter")
    public String filterByPrice(@RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice, Model theModel) {
        List<Car> theCars = carService.filterByPrice(minPrice, maxPrice);
        theModel.addAttribute("cars", theCars);
        return "list-cars";
    }
	
	@GetMapping("/unselect")
	public String unselectSelectedCars() {
		if(carsToDelete.size() != 0) {
			carsToDelete.clear();
		}
		return "redirect:/car/list";
	}
	
	@GetMapping("/removeFromBasket")
	public String removeFromBasket(Authentication auth) throws UsernameNotFoundException {
		User user = userService.getUser(userService.loadUserId(auth.getName().toString()));
		List<Car> allCars = carService.getCars();
		Car currentCar = new Car();
		for(int i = 0; i < allCars.size(); i++) {
			if(allCars.get(i).getUserId() == null) {
				continue;
			}
			
			if(allCars.get(i).getUserId().getId() == user.getId()) {
				currentCar = allCars.get(i);
				currentCar.setUserId(null);
				carService.saveCar(currentCar);
			}
		}
		return "redirect:/car/list";
	}
}
