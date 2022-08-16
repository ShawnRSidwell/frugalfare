package com.frugalfare.spring.service;

import java.util.List;

import com.frugalfare.spring.entity.Ride;

public interface RideService {

	public List<Ride> getRides();

	public void saveRide(Ride theRide);

	public Ride getRide(int rideId);

	public void deleteRide(int rideId);

	public List<Ride> getRidesByUserId(int userId);

}
