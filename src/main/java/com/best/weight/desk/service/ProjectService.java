package com.best.weight.desk.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.best.weight.desk.entities.BestWeightProject;
import com.best.weight.desk.entities.User;
import com.best.weight.desk.entities.WeightMesure;
import com.best.weight.desk.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	 @Autowired
	 ProjectRepository projectRepository;
	 
	 public Set<BestWeightProject> findByAuthor(User user){
		 return projectRepository.findByAuthor(user); 
	 }

	 public BestWeightProject addProject(BestWeightProject project, User author) {
    	project.setAuthor(author);
    	project.setStartDate(LocalDate.now());
    	double actualStartWeight = project.getStartWeight();
    	boolean looseWeight = actualStartWeight>project.getDesiredWeight();
		LocalDate expectedFinishDate = project.getStartDate();
		List<WeightMesure> expectedWeightMesures = new ArrayList<>();
		WeightMesure weightMesure = new WeightMesure();
		weightMesure.setMesureDate(expectedFinishDate);
		weightMesure.setWeight(actualStartWeight);
		expectedWeightMesures.add(weightMesure);
		
		while ((actualStartWeight>project.getDesiredWeight())||(actualStartWeight<project.getDesiredWeight()&&!looseWeight)) {
			weightMesure = new WeightMesure();
			if(looseWeight) {
				actualStartWeight-=(actualStartWeight*0.0075);
			}
			else {
				actualStartWeight+=(actualStartWeight*0.0075);
			}
			expectedFinishDate = expectedFinishDate.plusWeeks(1);
			weightMesure.setMesureDate(expectedFinishDate);
			weightMesure.setWeight(actualStartWeight);
		}
		
		project.setExpectedWeightMesures(expectedWeightMesures);

		return projectRepository.save(project);
	 }
}
