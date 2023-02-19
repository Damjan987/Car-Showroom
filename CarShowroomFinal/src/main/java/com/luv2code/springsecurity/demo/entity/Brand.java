package com.luv2code.springsecurity.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "brand")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "b_id")
	private int id;
	
	@Column(name = "brand_name")
	private String brandName;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "brandId", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Car> cars;
	
	public Brand() {}
	
	public Brand(String brandName) {
		this.brandName = brandName;
	}
	
	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}
