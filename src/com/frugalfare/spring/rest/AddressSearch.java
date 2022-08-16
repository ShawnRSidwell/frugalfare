package com.frugalfare.spring.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AddressSearch {
	public ObjectMapper objectMapper = new ObjectMapper();

	// takes a search string, changes spaces to "+" and uses Nomentin API to return
	// a location.

	public Location locationLookup(String location) {
		try {
			String Search = location.replace(" ", "+");

			URL website = new URL(
					"https://nominatim.openstreetmap.org/?addressdetails=1&q=" + Search + "&format=json&limit=1");

			List<Location> locations = objectMapper.readValue(website,
					objectMapper.getTypeFactory().constructCollectionType(List.class, Location.class));
			Location theLocation = locations.get(0);
			return theLocation;

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Address not found");
		}
		return null;
	}

	// takes the address searched and gets latitude as a double.
	public Double getLocationLat(String search) {

		return Double.parseDouble(locationLookup(search).getLat());
	}

	// takes the address searched and gets longitude as a double.
	public Double getLocationLong(String search) {

		return Double.parseDouble(locationLookup(search).getLon());
	}

	// gets the full address name of the search
	public String getDisplayName(String search) {

		return locationLookup(search).getDisplayName();
	}

//-----------TESTING-------
//
//	public static void main(String[] args) {
//
//		System.out.println(locationSearch("4554eastmarionway"));
//
////		ObjectMapper objectMapper = new ObjectMapper();
////		try {
////			String Search = "4554 east marion way".replace(" ", "+");
////
////			URL website = new URL(
////					"https://nominatim.openstreetmap.org/?addressdetails=1&q=" + Search + "&format=json&limit=1");
////
////			List<Location> locations = objectMapper.readValue(website,
////					objectMapper.getTypeFactory().constructCollectionType(List.class, Location.class));
////			System.out.println(locations);
////			Location theLocation = locations.get(0);
////			System.out.println(theLocation.getLat());
////			System.out.println(theLocation.getLon());
////			System.out.println(theLocation.getDisplayName());
////
////		} catch (JsonParseException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (JsonMappingException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (MalformedURLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (IOException e) {
////			System.out.println("Address not found");
////		}
//	}
}
