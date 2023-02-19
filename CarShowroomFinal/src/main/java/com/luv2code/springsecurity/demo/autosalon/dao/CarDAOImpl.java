package com.luv2code.springsecurity.demo.autosalon.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springsecurity.demo.entity.Car;

@Repository
public class CarDAOImpl implements CarDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Car> getCars() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Car> theQuery = currentSession.createQuery("from Car", Car.class);
		List<Car> cars = theQuery.getResultList();
		return cars;
	}
	
	@Override
	public void saveCar(Car theCar) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theCar);
	}

	@Override
	public Car getCar(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Car theCar = currentSession.get(Car.class, theId);
		return theCar;
	}

	@Override
	public void deleteCar(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("delete from Car where id=:carId");
		theQuery.setParameter("carId", theId);
		theQuery.executeUpdate();
	}
	
	
/*	public List<Car> viewAllCars(int offset, int noOfRecords) {
		String query = "select SQL_CALC_FOUND_ROWS * from car limit " + offset + ", " + noOfRecords;
		List<Car> carList = new ArrayList<Car>();
		Car car = null;

		return car;
		
	}*/
	
	@Override
    public List<Car> searchCars(String theSearchName) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery = null;

        if (theSearchName != null && theSearchName.trim().length() > 0) {
            theQuery =currentSession.createQuery("from Car where lower(model) like :theName or lower(brandId.brandName) like :theName", Car.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            theQuery =currentSession.createQuery("from Car", Car.class);
        }
        List<Car> cars = theQuery.getResultList();
        return cars;
    }
	
	@Override
    public List<Car> filterByPrice(double minPrice, double maxPrice) {
		Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery = null;
                
        theQuery = currentSession.createQuery("from Car where price between :min and :max", Car.class);
        theQuery.setParameter("min", minPrice);
        theQuery.setParameter("max", maxPrice);
       
        List<Car> cars = theQuery.getResultList();
        return cars;
    }
}
