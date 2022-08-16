package com.frugalfare.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frugalfare.spring.dao.RideDAO;
import com.frugalfare.spring.entity.Ride;

@Service
public class RideServiceImpl implements RideService {

	@Autowired
	private RideDAO rideDAO;

	@Override
	@Transactional
	public List<Ride> getRides() {

		return rideDAO.getRides();
	}

	@Override
	@Transactional
	public void saveRide(Ride theRide) {

		rideDAO.saveRide(theRide);
	}

	@Override
	@Transactional
	public Ride getRide(int rideId) {

		return rideDAO.getRide(rideId);
	}

	@Override
	@Transactional
	public void deleteRide(int rideId) {

		rideDAO.deleteRide(rideId);

	}

	@Override
	@Transactional
	public List<Ride> getRidesByUserId(int userId) {
		return rideDAO.getRidesByUserId(userId);
	}

}
