package com.luv2code.springsecurity.demo.autosalon.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springsecurity.demo.entity.Brand;

@Repository
public class BrandDAOImpl implements BrandDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Brand> getBrands() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Brand> theQuery = currentSession.createQuery("from Brand", Brand.class);
		List<Brand> brands = theQuery.getResultList();
		return brands;
	}
	
	@Override
	public void saveBrand(Brand theBrand) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theBrand);
	}

	@Override
	public Brand getBrand(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Brand theBrand = currentSession.get(Brand.class, theId);
		return theBrand;
	}

	@Override
	public void deleteBrand(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("delete from Brand where b_id=:brandId");
		theQuery.setParameter("brandId", theId);
		theQuery.executeUpdate();
	}
}
