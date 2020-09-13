package com.best.weight.desk.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.best.weight.desk.entities.BestWeightProject;
import com.best.weight.desk.entities.User;
import com.best.weight.desk.service.ProjectService;
import com.best.weight.desk.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	ProjectService projectService;
	
	@GetMapping("{id}")
	 public User get(
	            @PathVariable("id") User userFromDB
	    ) {
	        return userFromDB;
	 }
}
