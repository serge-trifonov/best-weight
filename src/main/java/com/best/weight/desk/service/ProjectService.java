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
import com.best.weight.desk.repositories.WeightMesureRepository;

import representations.ProjectRepresentation;

@Service
public class ProjectService {
	
	 @Autowired
	 ProjectRepository projectRepository;
		
	 @Autowired
	 WeightMesureRepository weightMesureRepository;
	 
	 public BestWeightProject findByAuthor(User user){
		 return projectRepository.findByAuthor(user); 
	 }

	 public ProjectRepresentation addProject(BestWeightProject project) {
		BestWeightProject existingProject = projectRepository.findByAuthor(project.getAuthor());
		if(existingProject!=null) {
			 projectRepository.delete(existingProject);
		}
		 
    	project.setStartDate(LocalDate.now().minusWeeks(2));
    	double actualStartWeight = project.getStartWeight();
    	boolean looseWeight = actualStartWeight>project.getDesiredWeight();
		LocalDate expectedFinishDate = project.getStartDate();
		List<WeightMesure> expectedWeightMesures = new ArrayList<>();
		List<WeightMesure> weightMesures = new ArrayList<>();
		WeightMesure weightMesure = new WeightMesure();
		weightMesure.setMesureDate(expectedFinishDate);
		weightMesure.setWeight(actualStartWeight);
		weightMesureRepository.save(weightMesure);
		expectedWeightMesures.add(weightMesure);
		weightMesures.add(weightMesure);
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
			expectedWeightMesures.add(weightMesure);
		}
		project.setExpectedFinishDate(expectedFinishDate);
		project.setWeightMesures(weightMesures);
		return new ProjectRepresentation(projectRepository.save(project));
	 }

	public ProjectRepresentation addWeight(User user) {
		
		BestWeightProject project = projectRepository.findByAuthor(user);
		if(project == null) {
			return null;
		}
		List <WeightMesure> weightMesures = project.getWeightMesures();
		
		WeightMesure wm = weightMesures.stream().filter(m->m.getMesureDate().equals(LocalDate.now())).findAny().orElse(null);

		if(wm!=null) {
			weightMesureRepository.delete(wm);
			weightMesures.remove(wm);
		}
		
		WeightMesure weightMesure= new WeightMesure();
		weightMesure.setMesureDate(LocalDate.now());
		weightMesureRepository.save(weightMesure);
		weightMesures.add(weightMesure);
		return new ProjectRepresentation(projectRepository.save(project));
	}
}
