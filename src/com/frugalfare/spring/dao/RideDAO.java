package com.frugalfare.spring.dao;

import java.util.List;

import com.frugalfare.spring.entity.Ride;

public interface RideDAO {

	public List<Ride> getRides();

	public List<Ride> getRidesByUserId(int userId);

	public void saveRide(Ride theRide);

	public Ride getRide(int rideId);

	public void deleteRide(int rideId);

}
