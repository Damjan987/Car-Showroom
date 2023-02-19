package com.luv2code.springsecurity.demo.autosalon.service;

import java.util.List;

import com.luv2code.springsecurity.demo.entity.Brand;
import com.luv2code.springsecurity.demo.entity.Car;

public interface CarService {
	public List<Car> getCars();
	public void saveCar(Car theCar);
	public Car getCar(int theId);
	public void deleteCar(int theId);
	public List<Car> searchCars(String theSearchName);
	public void saveCar1(String file, String name, Brand theBrand, int price);
	public List<Car> filterByPrice(double minPrice, double maxPrice);
}
