package com.frugalfare.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.frugalfare.spring.entity.Ride;

@Repository
public class RideDAOImpl implements RideDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Ride> getRides() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Ride> theQuery = currentSession.createQuery("from Ride", Ride.class);

		List<Ride> rides = theQuery.getResultList();

		return rides;
	}

	public List<Ride> getRidesByUserId(int userId) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Ride> theQuery = currentSession.createQuery("from Ride where user_id=:userId", Ride.class);
		theQuery.setParameter("userId", userId);
		List<Ride> rides = theQuery.getResultList();
		return rides;
	}

	@Override
	public void saveRide(Ride theRide) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theRide);

	}

	@Override
	public Ride getRide(int rideId) {
		Session currentSession = sessionFactory.getCurrentSession();

		Ride currentRide = currentSession.get(Ride.class, rideId);

		return currentRide;
	}

	@Override
	public void deleteRide(int rideId) {
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = currentSession.createQuery("delete from Ride where id=:rideId");
		theQuery.setParameter("rideId", rideId);

		theQuery.executeUpdate();

	}

}
