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
import org.springframework.web.bind.annotation.RequestParam;

import com.frugalfare.spring.entity.Ride;
import com.frugalfare.spring.entity.User;
import com.frugalfare.spring.service.RideService;
import com.frugalfare.spring.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private RideService rideService;

	@Autowired
	private PasswordEncoder encoder;
	
	
	//Get login page.
	@GetMapping("/login")
	public String showLoginForm(Model theModel) {
		theModel.addAttribute("user", new User());
		return "login";
	}
	
	//Check the database to see if the client logged in correctly
	@PostMapping("/login")
	public String verifyLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session, Model theModel) {

		// Find user by their email
		User user = userService.findUserByEmail(email);
		
		//Check if user exists or if the client has typed in the correct matching password
		if (user == null || !encoder.matches(password, user.getPassword())) {
			theModel.addAttribute("loginError", "Error logging in. Please try again");
			theModel.addAttribute("user", new User());
			return "login";
		}
		
		//Add logged in user to session. 
		session.setAttribute("loggedInUser", user);
		return "redirect:/search";
	}
	
	//Remove logged in user from Session
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loggedInUser");
		return "redirect:/search";
	}
	
	@GetMapping("/updateAccount")
	public String updateAccount(@RequestParam("userId") int theId, Model theModel) {

		// get the user from our service
		User theUser = userService.getUser(theId);

		// set user as a model attribute to pre-populate the form
		theModel.addAttribute("user", theUser);

		// send over to our form
		return "register";
	}

	@GetMapping("/register")
	public String showFormForAddAdmin(Model theModel) {

		// create model attribute to bind form data
		User theUser = new User();

		// add empty user to the model
		theModel.addAttribute("user", theUser);

		return "register";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User theUser, HttpSession session) {
		
		//Encrypt their password useing Bcrypt
		String protectedPassword = encoder.encode(theUser.getPassword());
		
		//Save the user's password to the model 
		theUser.setPassword(protectedPassword);
		
		//Save the user using our Service
		userService.saveUser(theUser);
		
		//Keep the recently added user logged in
		session.setAttribute("loggedInUser", theUser);

		return "redirect:/search";
	}

	@GetMapping("/recentRides")
	public String listRidesById(@RequestParam("userId") int theId, Model theModel) {

		User theUser = userService.getUser(theId);

		List<Ride> rides = rideService.getRidesByUserId(theUser.getId());

		theModel.addAttribute("ridesById", rides);

		return "recent-rides";
	}

}
