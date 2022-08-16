package com.frugalfare.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.frugalfare.spring.entity.Ride;
import com.frugalfare.spring.entity.User;
import com.frugalfare.spring.service.RideService;
import com.frugalfare.spring.service.UserService;

@Controller
@RequestMapping("/user")
public class AdminController {

	// need to inject our user service
	@Autowired
	private UserService userService;
	
	@Autowired
	private RideService rideService;

	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("/admin")
	public String listUsersAdmin(Model theModel) {

		// get users from the service
		List<User> theUsers = userService.getUsers();
		
		// add the users to the model
				theModel.addAttribute("users", theUsers);

		
		List<Ride> theRides = rideService.getRides();

		// add the users to the model
		theModel.addAttribute("rides", theRides);

		return "admin";
	}

	@GetMapping("/signUpForm")
	public String showFormForAddAdmin(Model theModel) {

		// create model attribute to bind form data
		User theUser = new User();

		// add empty user to the model
		theModel.addAttribute("user", theUser);

		return "signup-form";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User theUser, HttpSession session) {

		String protectedPassword = encoder.encode(theUser.getPassword());
		System.out.println(protectedPassword);

		theUser.setPassword(protectedPassword);
		// save the user using our service
		userService.saveUser(theUser);

		session.setAttribute("loggedInUser", theUser);

		return "redirect:/user/admin";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") int theId, Model theModel) {

		// get the user from our service
		User theUser = userService.getUser(theId);

		// set user as a model attribute to pre-populate the form
		theModel.addAttribute("user", theUser);

		// send over to our form
		return "signup-form";
	}

	@GetMapping("/delete")
	public String deleteUser(@RequestParam("userId") int theId) {

		// save the user using our service
		userService.deleteUser(theId);

		return "redirect:/user/admin";
	}
	
	@GetMapping("/deleteRide")
	public String deleteRide(@RequestParam("rideId") int rideId) {

		// save the user using our service
		rideService.deleteRide(rideId);

		return "redirect:/user/admin";
	}


}
