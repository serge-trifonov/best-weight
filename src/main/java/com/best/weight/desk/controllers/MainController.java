package com.best.weight.desk.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.best.weight.desk.entities.User;
import com.best.weight.desk.service.UserService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
public class MainController {   
	@Autowired
	UserService userService;
	
	@PostMapping("/registration")
	public String create(Model model, @RequestBody User user) {
		userService.create(user);
		model.addAttribute("isDevMode", true);
		return "index";
	}

    @GetMapping   
    public String main(Model model, @AuthenticationPrincipal User user) throws AuthenticationException, Exception {
    	model.addAttribute("userAuth", user);
    	model.addAttribute("isDevMode", true);
        return "index"; 
    }
    
    @GetMapping("*")
    public String defaultPath( Model model, @AuthenticationPrincipal User user) {
    	model.addAttribute("userAuth", user);
    	model.addAttribute("isDevMode", true);
        return "index";
    }
    
    @GetMapping("/login")
    public String login( Model model, @AuthenticationPrincipal User user) {
    	model.addAttribute("userAuth", user);
    	model.addAttribute("isDevMode", true);
        return "index"; 
    }   
}
