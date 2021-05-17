package me.max.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.max.models.User;

//Will need session annotation, saving user once login is validated
@RestController
@RequestMapping("/")
public class LoginController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	// Model session attr
	// Default serve index
    public String index() {
        return "index";
    }
// Check user credentials
// Send to appropriate page
    @PostMapping("login")
    public User loginUser(@RequestBody User attempt) {

    	this.jdbcTemplate.queryForObject(null, null)
    	
    }
// Can also handle logout here
}
