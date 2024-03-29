package org.patsimas.login.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@GetMapping("/")
	public String loginPage() {
		return ("<h1>Welcome</h1>");
	}
	
	@GetMapping("/user")
	public String userPage() {
		return ("<h1>Welcome User</h1>");
	}
	
	@GetMapping("/admin")
	public String adminPage() {
		return ("<h1>Welcome Admin</h1>");
	}

}

