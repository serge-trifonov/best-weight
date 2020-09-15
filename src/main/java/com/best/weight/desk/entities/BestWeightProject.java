package com.best.weight.desk.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class BestWeightProject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private LocalDate startDate;
	private LocalDate expectedFinishDate;
	@OneToMany(cascade = {CascadeType.ALL})
	private List<WeightMesure> weightMesures;
	private double currentWeight;
	private Double desiredWeight;
	private Double startWeight;
	
	@OneToOne
	private User author;
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public List<WeightMesure> getWeightMesures() {
		return weightMesures;
	}
	
	public void setWeightMesures(List<WeightMesure> weightMesures) {
		this.weightMesures = weightMesures;
	}
	
	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}

	public Double getDesiredWeight() {
		return desiredWeight;
	}
	
	public void setDesiredWeight(Double desiredWeight) {
		this.desiredWeight = desiredWeight;
	}
	
	public Double getStartWeight() {
		return startWeight;
	}
	
	public void setStartWeight(Double startWeight) {
		this.startWeight = startWeight;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDate getExpectedFinishDate() {
		return expectedFinishDate;
	}
	
	public void setExpectedFinishDate(LocalDate expectedFinishDate) {
		this.expectedFinishDate = expectedFinishDate;
	}
	
	public double getCurrentWeight() {
		return currentWeight;
	}
	
	public void setCurrentWeight(double currentWeight) {
		this.currentWeight = currentWeight;
	}
	
}
