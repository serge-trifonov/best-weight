package com.best.weight.desk.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
public class WeightMesure implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private LocalDate mesureDate;
	private Double weight;
	
	public LocalDate getMesureDate() {
		return mesureDate;
	}
	public void setMesureDate(LocalDate mesureDate) {
		this.mesureDate = mesureDate;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
}
