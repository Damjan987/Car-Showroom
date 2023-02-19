package com.luv2code.springsecurity.demo.autosalon.service;

import java.util.List;

import com.luv2code.springsecurity.demo.entity.*;

public interface BrandService {
	public List<Brand> getBrands();
	public void saveBrand(Brand theBrand);
	public Brand getBrand(int theId);
	public void deleteBrand(int theId);
}
