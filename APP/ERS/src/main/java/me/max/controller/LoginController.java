package me.max.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Will need session annotation, saving user once login is validated
@Controller
@RequestMapping("/")
public class LoginController {
	// Model session attr
	// Default serve index
    public String index() {
    	System.out.println(1);
        return "index";
    }
// Check user credentials
// Send to appropriate page
// Can also handle logout here
}
