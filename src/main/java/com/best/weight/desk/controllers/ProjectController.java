package com.best.weight.desk.controllers;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.best.weight.desk.entities.BestWeightProject;
import com.best.weight.desk.entities.User;
import com.best.weight.desk.service.ProjectService;

@RestController
@RequestMapping("projects")
public class ProjectController {
	
	 @Autowired
	 ProjectService projectService;
	 
	 @GetMapping   
	 public BestWeightProject main(Model model, @AuthenticationPrincipal User author) throws AuthenticationException, Exception {
		 BestWeightProject project = projectService.findByAuthor(author);
	     return project; 
	  }
	 
	 @PostMapping
	 public BestWeightProject create(
	           @RequestBody BestWeightProject project
	    ) {
		 	return projectService.addProject(project);
	 }

}
