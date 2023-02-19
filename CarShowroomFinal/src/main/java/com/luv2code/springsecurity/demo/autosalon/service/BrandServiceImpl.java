package com.luv2code.springsecurity.demo.autosalon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springsecurity.demo.autosalon.dao.BrandDAO;
import com.luv2code.springsecurity.demo.entity.*;


@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDAO brandDAO;
	
	@Override
	@Transactional
	public List<Brand> getBrands() {
		return brandDAO.getBrands();
	}
	
	@Override
	@Transactional
	public void saveBrand(Brand theBrand) {
		brandDAO.saveBrand(theBrand);
	}

	@Override
	@Transactional
	public Brand getBrand(int theId) {
		return brandDAO.getBrand(theId);
	}

	@Override
	@Transactional
	public void deleteBrand(int theId) {
		brandDAO.deleteBrand(theId);
	}
}