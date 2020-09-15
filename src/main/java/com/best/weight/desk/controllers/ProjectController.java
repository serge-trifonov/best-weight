package com.best.weight.desk.controllers;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.best.weight.desk.entities.BestWeightProject;
import com.best.weight.desk.entities.User;
import com.best.weight.desk.repositories.ProjectRepository;
import com.best.weight.desk.service.ProjectService;
import com.best.weight.desk.service.UserService;

import representations.ProjectRepresentation;

@RestController
@RequestMapping("projects")
public class ProjectController {
	 @Autowired
	 UserService userService;
	
	 @Autowired
	 ProjectService projectService;
	 
	 @GetMapping   
	 public ProjectRepresentation main(@AuthenticationPrincipal User author) throws AuthenticationException, Exception {
		 BestWeightProject project = projectService.findByAuthor(author);
		 if (project == null) {
			 return null;
		 }
	     ProjectRepresentation representation =  new ProjectRepresentation(project); 
	     return representation;
	  }
	 
	 @PostMapping
	 public ProjectRepresentation create(
	           @RequestBody BestWeightProject project
	    ) {
		 	return projectService.addProject(project);
	 }
	 
	 @PostMapping("/add/{id}")
	 public ProjectRepresentation addWeight(
	            @PathVariable("id") User userFromDB, @RequestBody User user 
	    ) {
			BeanUtils.copyProperties(user, userFromDB, "id");
			userService.updateUser(userFromDB);
		 	return projectService.addWeight(userFromDB);
	 }

}
