package com.frugalfare.spring.service;

import java.util.List;

import com.frugalfare.spring.entity.User;

public interface UserService {
	public List<User> getUsers();

	public void saveUser(User theUser);

	public User getUser(int theId);

	public void deleteUser(int theId);

	public User findUserByEmail(String email);

	public User loginUser(String email, String password);
}
