package com.example.crudmysql.controller;

import java.util.List;

import com.example.crudmysql.model.User;
import com.example.crudmysql.model.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

    @Autowired
    private UserRepository userRepository;

	@GetMapping(path = "/")
	public String home() {
		return "home";
	}

    @GetMapping("/home")
    public String home(Model model) {
    	List<User> users =  (List<User>) userRepository.findAll();
    	
        model.addAttribute("users", users);
        return "home";
    }

    

    
}
