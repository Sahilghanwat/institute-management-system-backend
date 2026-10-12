package com.intitude_manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.intitude_manage.model.User;
import com.intitude_manage.service.User_Service;

@Controller
public class User_Controller {
	
	@Autowired
	private User_Service user_ser;
	
	//Show All User
	
	@GetMapping("/AllUsers")
	public String allUsers(Model model)
	{
		model.addAttribute("listUser", user_ser.getAllUser());
		return "View_All_User";
	}
	
	
	//Save Users
	@GetMapping("/registrationFrom")
	public String registeration_Form(Model model)
	{
		User user = new User();
		model.addAttribute("userdata", user);
		return "Registeration";
	}
	
	@Autowired
	private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("userdata") User user) {
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    user.setRole("STUDENT");
	    user_ser.saveUser(user);
	    return "redirect:/AllUsers";
	}
	
	
	

}
