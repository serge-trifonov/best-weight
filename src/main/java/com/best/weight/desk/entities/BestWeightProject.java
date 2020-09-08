package com.best.weight.desk.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@OneToMany
	private List<WeightMesure> weightMesures;
	@OneToMany
	private List<WeightMesure> expectedWeightMesures;
	private Double desiredWeight;
	private Double startWeight;
	private Boolean current;
	@ManyToOne
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
	public List<WeightMesure> getExpectedWeightMesures() {
		return expectedWeightMesures;
	}
	public void setExpectedWeightMesures(List<WeightMesure> expectedWeightMesures) {
		this.expectedWeightMesures = expectedWeightMesures;
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
	public Boolean getCurrent() {
		return current;
	}
	public void setCurrent(Boolean current) {
		this.current = current;
	}

}
