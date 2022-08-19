package com.frugalfare.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frugalfare.spring.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> getUsers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<User> theQuery = currentSession.createQuery("from User", User.class);

		// execute query and get result list
		List<User> users = theQuery.getResultList();

		// return the results
		return users;
	}

	@Override
	public void saveUser(User theUser) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save/update the user
		currentSession.saveOrUpdate(theUser);
	}

	public User getUser(int theId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using the primary key
		User theUser = currentSession.get(User.class, theId);

		return theUser;
	}

	@Override
	public void deleteUser(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// delete object with primary key
		User theUser = currentSession.get(User.class, theId);
		currentSession.remove(theUser);
	}

	public User findUserByEmail(String userEmail) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using the primary key
		Query<User> query = currentSession.createQuery("from User where email=:email", User.class);
		query.setParameter("email", userEmail);

		User user = (User) query.uniqueResult();

		return user;
	}

	@Override
	public User findUser(String userEmail, String userPassword) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> query = currentSession.createQuery("from User where email=:email and password=:password",
				User.class);
		query.setParameter("email", userEmail);
		query.setParameter("password", userPassword);
		User user = (User) query.uniqueResult();
		return user;
	}

}
