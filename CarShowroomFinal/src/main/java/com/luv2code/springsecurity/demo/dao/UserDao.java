package com.luv2code.springsecurity.demo.dao;

import java.util.List;

import com.luv2code.springsecurity.demo.entity.Brand;
import com.luv2code.springsecurity.demo.entity.User;

public interface UserDao {
    User findByUserName(String userName);
    void save(User user);
    public List<User> getBrands();
	public User getUser(int theId);
}
