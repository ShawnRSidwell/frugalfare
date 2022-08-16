package com.frugalfare.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frugalfare.spring.dao.UserDAO;
import com.frugalfare.spring.entity.User;

@Service
public class UserServiceImpl implements UserService {

	// need to inject DAO
	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	@Transactional
	public void saveUser(User theUser) {
		userDAO.saveUser(theUser);
	}

	@Override
	@Transactional
	public User getUser(int theId) {
		return userDAO.getUser(theId);
	}

	@Transactional
	public void deleteUser(int theId) {

		userDAO.deleteUser(theId);
	}

	@Transactional
	public User findUserByEmail(String email) {
		return userDAO.findUserByEmail(email);

	}

	@Override
	@Transactional
	public User loginUser(String email, String password) {
		User user = userDAO.findUser(email, password);
		return user;
	}

}
