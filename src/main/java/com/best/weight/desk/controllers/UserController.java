package com.best.weight.desk.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.best.weight.desk.entities.User;
import com.best.weight.desk.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("{id}")
	 public User create(
	            @PathVariable("id") User userFromDB, @RequestBody User user 
	    ) {
			System.out.println("user weight : "+user.getWeight());
			BeanUtils.copyProperties(user, userFromDB, "id");
			System.out.println("user weight : "+user.getWeight());
	        return userService.updateUser(userFromDB);
	 }
}
