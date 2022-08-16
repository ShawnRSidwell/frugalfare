package com.frugalfare.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.frugalfare.spring.converters.DistanceConverter;
import com.frugalfare.spring.converters.FareCalculator;
import com.frugalfare.spring.entity.Ride;
import com.frugalfare.spring.entity.User;
import com.frugalfare.spring.rest.AddressSearch;
import com.frugalfare.spring.service.RideService;
import com.frugalfare.spring.service.UserService;

@Controller
@RequestMapping("")
public class SearchController {

	@Autowired
	private RideService rideService;

	@Autowired
	private UserService userService;

	@GetMapping("/resultsAdmin")
	public String listRidesAdmin(Model theModel) {

		List<Ride> rides = rideService.getRides();

		theModel.addAttribute("rides", rides);

		return "list-rides";
	}

	@GetMapping("/results2")
	public String listRides(Model theModel) {

		List<Ride> rides = rideService.getRides();
		Ride lastRide = rides.get(rides.size() - 1);

		theModel.addAttribute("lastRide", lastRide);

		return "results-not-logged-in";
	}

	@GetMapping("/results")
	public String listRidesById(HttpSession session, Model theModel) {

		// Gets user from http session.
		User loggedInUser = (User) session.getAttribute("loggedInUser");

		//if user is not logged in redirect to results that do not contain recents rides
		if (loggedInUser == null) {
			return "redirect:/results2";
		}
		
		//obtain list of rides and add to model
		List<Ride> rides = rideService.getRidesByUserId(loggedInUser.getId());
		theModel.addAttribute("ridesById", rides);
		
		//Get last ride and add to model
		Ride lastRide = rides.get(rides.size() - 1);
		theModel.addAttribute("lastRide", lastRide);

		return "results";
	}

	@GetMapping("/search")
	public String getHomePage(Model theModel) {

		// create model attribute to bind form data
		Ride ride = new Ride();

		// add empty user to the model
		theModel.addAttribute("ride", ride);

		return "home";
	}

	@GetMapping("/delete")
	public String deleteRide(@RequestParam("rideId") int rideId) {

		// save the user using our service
		rideService.deleteRide(rideId);

		return "redirect:/results";
	}

	@GetMapping("/searchupdate")
	public String getHomePageUpdate(@RequestParam("rideId") int rideId, Model theModel) {

		Ride ride = rideService.getRide(rideId);

		theModel.addAttribute("ride", ride);

		return "home";
	}

	@PostMapping("/saveRide")
	public String saveRide(@ModelAttribute("ride") Ride ride, HttpSession session, Model theModel) {

		// Gets user from http session.
		User loggedInUser = (User) session.getAttribute("loggedInUser");

		// checks if user is logged in, if so associates ride to login.
		if (loggedInUser == null) {
			ride.setUser(null);
		} else {
			ride.setUser(loggedInUser);
		}

		// get starting address and ending address from model
		String startPoint;
		String endPoint;
		startPoint = ride.getStartAddress();
		endPoint = ride.getEndAddress();

		// get long/lat of each point
		AddressSearch search = new AddressSearch();
		double lat1;
		double long1;
		double lat2;
		double long2;
		try {
			lat1 = search.getLocationLat(startPoint);
			long1 = search.getLocationLong(startPoint);
		} catch (IndexOutOfBoundsException e) {
			// create model attribute to bind form data
			// add empty user to the model
			theModel.addAttribute("ride", new Ride());
			theModel.addAttribute("SearchError", "Unable to find starting location.");
			return "home";
		}

		try {
			lat2 = search.getLocationLat(endPoint);
			long2 = search.getLocationLong(endPoint);
		} catch (IndexOutOfBoundsException e) {
			theModel.addAttribute("ride", new Ride());
			theModel.addAttribute("SearchError", "Unable to find final destination.");
			return "home";
		}
		// get full address names from API
		String location1 = search.getDisplayName(startPoint);
		String location2 = search.getDisplayName(endPoint);

		// calculate distance (in miles) between the two locations.
		double miles = DistanceConverter.distance(lat1, long1, lat2, long2);
		// Set display, coordinates, distance and cost to model.

		FareCalculator fareCalc = new FareCalculator();

		ride.setStartAddress(location1);
		ride.setEndAddress(location2);
		ride.setLat1(lat1);
		ride.setLong1(long1);
		ride.setLat2(lat2);
		ride.setLong2(long2);
		ride.setMiles(Math.floor(miles * 100) / 100.00);
		ride.setTaxiPrice(fareCalc.calculateTaxiFare(miles));
		ride.setLyftPrice(fareCalc.calculateLyftFare(miles));
		ride.setUberPrice(fareCalc.calculateUberFare(miles));

		// save the user to the database using our service
		rideService.saveRide(ride);

		return "redirect:/results";
	}
	
	@GetMapping("/about")
	public String getAboutPage() {

		return "about";
	}


}
