package me.max.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Will need session annotation, saving user once login is validated
@Controller
public class LoginController {
	// Model session attr
	// Default serve index
	@GetMapping("/")
	public String index() {
		return "index";
	}
// Check user credentials
// Send to appropriate page
// Can also handle logout here
}
