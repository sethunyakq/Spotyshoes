package com.example.Phase3_Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Phase3_Project.model.User;
import com.example.Phase3_Project.services.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/admin/login")
	public String showLoginPage() {
		return "login";
	}

	@PostMapping("/admin/login")
	public String processLogin(@RequestParam String email, @RequestParam String password, Model model) {
		//Validate the user's credentials
		
		User user = userService.getUserByEmail(email);
		
		if(user != null && user.getPassword().equals(password)) {
			//successful login, redirect to admin dashboard
			return "redirect:/admin/dashboard";
		}
		else {
			model.addAttribute("errorMessage","Error Login");
			return "login";
		}		
	}
	
	@GetMapping("/admin/changepassword")
	public String showChangePasswordPage() {
		return "changepassword";
		
	}
	
	@PostMapping("/admin/changepassword")
	public String processChangePassword(@RequestParam String email,
					@RequestParam String newPassword,
					@RequestParam String confirmPassword, Model model
			) {
		User user = userService.getUserByEmail(email);
		if(user != null) {
			if(newPassword.equals(confirmPassword)) {
				user.setPassword(newPassword);
				userService.saveUser(user);
				return "redirect:/admin/login";
			}
			
			else {
				//Passwords dont match, show the change password page with error message
				model.addAttribute("errorMessage","Paswwords dont match");
				return "changepassword";
				
			}
		}
		else {
			//if the user not found,  
			model.addAttribute("errorMessage","Email doesnt exist");
			return "changepassword";
		}
	}
	
	@GetMapping("/admin/logout")
	public String logout() {
		return "redirect:/admin/login";
	}
}
