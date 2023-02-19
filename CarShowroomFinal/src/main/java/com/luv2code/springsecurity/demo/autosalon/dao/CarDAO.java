package com.luv2code.springsecurity.demo.autosalon.dao;

import java.util.List;

import com.luv2code.springsecurity.demo.entity.Car;

public interface CarDAO {
	public List<Car> getCars();
	public void saveCar(Car theCar);
	public Car getCar(int theId);
	public void deleteCar(int theId);
	public List<Car> searchCars(String theSearchName);
	public List<Car> filterByPrice(double minPrice, double maxPrice);
}
