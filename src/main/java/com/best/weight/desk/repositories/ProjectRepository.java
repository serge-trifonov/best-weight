package com.best.weight.desk.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.best.weight.desk.entities.BestWeightProject;
import com.best.weight.desk.entities.User;



public interface ProjectRepository extends JpaRepository<BestWeightProject, Long>{
	public Set<BestWeightProject> findByAuthor(User user);
}
