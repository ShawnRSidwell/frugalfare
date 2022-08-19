package com.frugalfare.spring.converters;

public class DistanceConverter {

	//Calculate the distance between latitude and longitude
	public static double distance(double lat1, double long1, double lat2, double long2) {
		double theta = long1 - long2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return (dist);
	}
	
	//Converts degrees to radians
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	//Converts radians to degrees
	private static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

}
