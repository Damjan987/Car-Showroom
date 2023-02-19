package com.luv2code.springsecurity.demo.autosalon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springsecurity.demo.autosalon.dao.*;
import com.luv2code.springsecurity.demo.entity.Brand;
import com.luv2code.springsecurity.demo.entity.Car;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarDAO carDAO;
	
	@Override
	@Transactional
	public List<Car> getCars() {
		return carDAO.getCars();
	}
	
	@Override
	@Transactional
	public void saveCar(Car theCar) {
		carDAO.saveCar(theCar);
	}

	@Override
	@Transactional
	public Car getCar(int theId) {
		return carDAO.getCar(theId);
	}

	@Override
	@Transactional
	public void deleteCar(int theId) {
		carDAO.deleteCar(theId);
	}
	
	@Override
	@Transactional
	public void saveCar1(String file, String name, Brand theBrand, int price) {
		Car car = new Car();
		
		car.setImage(file);
		car.setBrandId(theBrand);
		car.setPrice(price);
		car.setModel(name);
		
		carDAO.saveCar(car);
	}
	
	@Override
    @Transactional
    public List<Car> searchCars(String theSearchName) {
        return carDAO.searchCars(theSearchName);
    }
	
	@Override
    @Transactional
    public List<Car> filterByPrice(double minPrice, double maxPrice) { 
        return carDAO.filterByPrice(minPrice, maxPrice);
    }
}
