package representations;

import java.time.LocalDate;

import com.best.weight.desk.entities.WeightMesure;

public class WeightMesureRepresentation {
	private LocalDate mesureDate;
	private double weight;
	
	public WeightMesureRepresentation(WeightMesure weightMesure) {
		this.mesureDate = weightMesure.getMesureDate();
		this.weight = weightMesure.getWeight();
	}
	
	public WeightMesureRepresentation() {
		
	}
	
	public LocalDate getMesureDate() {
		return mesureDate;
	}
	public void setMesureDate(LocalDate mesureDate) {
		this.mesureDate = mesureDate;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	
}
