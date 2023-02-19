package com.luv2code.springsecurity.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "car")
public class Car {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="model")
	private String model;
	
	@Column(name="price")
	private double price;
	
	@Column(name="image")
	private String image; 
	            
	@ManyToOne(fetch=FetchType.EAGER, optional=true )
	@JoinColumn(name="brand_id")
	private Brand brandId;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=true )
	@JoinColumn(name="user_id")
	private User userId;
	
	public Car() {}
	
	public Car(String model, double price, String image, Brand brandId) {
		this.model = model;
		this.price = price;
		this.image = image;
		this.brandId = brandId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public Brand getBrandId() {
		return brandId;
	}
	public void setBrandId(Brand brandId) {
		this.brandId = brandId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}
}
