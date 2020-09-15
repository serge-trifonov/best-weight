package representations;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.best.weight.desk.entities.BestWeightProject;

public class ProjectRepresentation {
	private LocalDate startDate;
	private LocalDate expectedFinishDate;
	private List<WeightMesureRepresentation> weightMesures;
	private List<WeightMesureRepresentation> expectedWeekWeightMesures;
	private List<WeightMesureRepresentation> weekWeightMesures;
	private List<WeightMesureRepresentation> expectedDayWeightMesures;
	private Double desiredWeight;
	private Double startWeight;
	
	public ProjectRepresentation(BestWeightProject project) {
		this.startDate = project.getStartDate();
		this.expectedFinishDate = project.getExpectedFinishDate();
		this.weightMesures = project.getWeightMesures().stream().map(m->new WeightMesureRepresentation(m)).collect(Collectors.toList());
		this.desiredWeight = project.getDesiredWeight();
		this.startWeight = project.getStartWeight();
		countExpectedWeekWeightMesures();
	}
	
	
	private void countExpectedWeekWeightMesures() {
		double actualStartWeight = startWeight;
    	boolean looseWeight = actualStartWeight>desiredWeight;
		LocalDate expectedFinishDate = startDate;
		this.expectedWeekWeightMesures = new ArrayList<>();
		this.expectedWeekWeightMesures = new ArrayList<>();
		this.weekWeightMesures = new ArrayList<>();
		this.expectedDayWeightMesures = new ArrayList<>();
		WeightMesureRepresentation weightMesure = new WeightMesureRepresentation();
		weightMesure.setMesureDate(expectedFinishDate);
		weightMesure.setWeight(startWeight);
		this.expectedWeekWeightMesures.add(weightMesure);
		this.weekWeightMesures.add(weightMesure);
		this.expectedDayWeightMesures.add(weightMesure);
		for (int i=1; i<weightMesures.size(); i++) {
			double difference = weightMesures.get(i-1).getWeight()-weightMesures.get(i).getWeight();
			long steps = ChronoUnit.DAYS.between(weightMesures.get(i-1).getMesureDate(), weightMesures.get(i).getMesureDate())-1;
			double addUnit = difference/steps;
			LocalDate date = weightMesures.get(i-1).getMesureDate().plusDays(1);
			double weight = weightMesures.get(i-1).getWeight()-addUnit;
			for(int j=0; j<steps; j++) {
				WeightMesureRepresentation mesure = new WeightMesureRepresentation();
				mesure.setMesureDate(date);
				mesure.setWeight(weight);
				date = date.plusDays(1);
				weight = weight-addUnit;
				this.weightMesures.add(i, mesure);
				i++;
			}
		}
		while ((actualStartWeight>desiredWeight)||(actualStartWeight<desiredWeight&&!looseWeight)) {
			weightMesure = new WeightMesureRepresentation();
			if(looseWeight) {
				actualStartWeight-=(actualStartWeight*0.0075);
			}
			else {
				actualStartWeight+=(actualStartWeight*0.0075);
			}
			expectedFinishDate = expectedFinishDate.plusWeeks(1);
			LocalDate actualDate = expectedFinishDate;
			WeightMesureRepresentation mes = weightMesures.stream().filter(m->m.getMesureDate().equals(actualDate)).findAny().orElse(null);
			
			if(mes!=null) {
				weekWeightMesures.add(mes);
			}
			
			weightMesure.setMesureDate(expectedFinishDate);
			weightMesure.setWeight(actualStartWeight);
			expectedWeekWeightMesures.add(weightMesure);
		}
		
		for (int i=1; i<this.expectedWeekWeightMesures.size(); i++) {
			double difference = expectedWeekWeightMesures.get(i-1).getWeight()-this.expectedWeekWeightMesures.get(i).getWeight();
			long steps = ChronoUnit.DAYS.between(expectedWeekWeightMesures.get(i-1).getMesureDate(), expectedWeekWeightMesures.get(i).getMesureDate());
			double addUnit = difference/steps;
			LocalDate date = expectedWeekWeightMesures.get(i-1).getMesureDate().plusDays(1);
			double weight = expectedWeekWeightMesures.get(i-1).getWeight()-addUnit;
			for(int j=0; j<steps; j++) {
				WeightMesureRepresentation mesure = new WeightMesureRepresentation();
				mesure.setMesureDate(date);
				mesure.setWeight(weight);
				date = date.plusDays(1);
				weight = weight-addUnit;
				this.expectedDayWeightMesures.add(mesure);
			} 
			
		}
		
		this.expectedDayWeightMesures = this.expectedDayWeightMesures.stream().filter(m->m.getMesureDate().isAfter(LocalDate.now().minusMonths(1))
				&&m.getMesureDate().isBefore(LocalDate.now().plusDays(1))).collect(Collectors.toList());
		this.weightMesures = this.weightMesures.stream().filter(m->m.getMesureDate().isAfter(LocalDate.now().minusMonths(1))
				&&m.getMesureDate().isBefore(LocalDate.now().plusDays(1))).collect(Collectors.toList());
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getExpectedFinishDate() {
		return expectedFinishDate;
	}


	public void setExpectedFinishDate(LocalDate expectedFinishDate) {
		this.expectedFinishDate = expectedFinishDate;
	}


	public List<WeightMesureRepresentation> getWeightMesures() {
		return weightMesures;
	}


	public void setWeightMesures(List<WeightMesureRepresentation> weightMesures) {
		this.weightMesures = weightMesures;
	}


	public List<WeightMesureRepresentation> getExpectedWeekWeightMesures() {
		return expectedWeekWeightMesures;
	}


	public void setExpectedWeekWeightMesures(List<WeightMesureRepresentation> expectedWeekWeightMesures) {
		this.expectedWeekWeightMesures = expectedWeekWeightMesures;
	}


	public List<WeightMesureRepresentation> getWeekWeightMesures() {
		return weekWeightMesures;
	}


	public void setWeekWeightMesures(List<WeightMesureRepresentation> weekWeightMesures) {
		this.weekWeightMesures = weekWeightMesures;
	}


	public List<WeightMesureRepresentation> getExpectedDayWeightMesures() {
		return expectedDayWeightMesures;
	}


	public void setExpectedDayWeightMesures(List<WeightMesureRepresentation> expectedDayWeightMesures) {
		this.expectedDayWeightMesures = expectedDayWeightMesures;
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
	
	public boolean getCurrent() {
		return this.expectedFinishDate.isAfter(LocalDate.now());
	}
}
