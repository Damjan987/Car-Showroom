package com.luv2code.springsecurity.demo.autosalon.dao;

import java.util.List;

import com.luv2code.springsecurity.demo.entity.Brand;

public interface BrandDAO {
	public List<Brand> getBrands();
	public void saveBrand(Brand theBrand);
	public Brand getBrand(int theId);
	public void deleteBrand(int theId);
}
