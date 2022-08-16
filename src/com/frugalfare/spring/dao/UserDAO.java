package com.frugalfare.spring.dao;

import java.util.List;

import com.frugalfare.spring.entity.User;

public interface UserDAO {

	public List<User> getUsers();

	public void saveUser(User theUser);

	public User getUser(int theId);

	public void deleteUser(int theId);

	public User findUser(String userEmail, String userPassword);

	public User findUserByEmail(String userEmail);

}
