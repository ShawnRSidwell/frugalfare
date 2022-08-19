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

	
	
	
	public Location locationLookup(String location) {
		try {
			
			//replaces spaces with a "+" sign. 
			String Search = location.replace(" ", "+");
			
			//Adds search to API URL
			URL website = new URL(
					"https://nominatim.openstreetmap.org/?addressdetails=1&q=" + Search + "&format=json&limit=1");
			
			//Maps the JSON file returned from the URL
			List<Location> locations = objectMapper.readValue(website,
					objectMapper.getTypeFactory().constructCollectionType(List.class, Location.class));
			
			//JSON returns an array. Gets first result from array and returns it.
			Location theLocation = locations.get(0);
			return theLocation;

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
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

}
