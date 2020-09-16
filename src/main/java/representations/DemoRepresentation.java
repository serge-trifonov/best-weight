package representations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.best.weight.desk.entities.Demo;

public class DemoRepresentation {
	private List<String> allcategories;
	private List<Double> alldates;
	private LocalDate expectedFinishDate;
	private double height;
	private double weight;
	private double desiredWeight;
	
	public DemoRepresentation(Demo demo) {
		this.height = demo.getHeight();
		this.weight = demo.getWeight();
		this.desiredWeight = demo.getDesiredWeight();
		countDemo();
	}
	
	public void countDemo() {
		boolean looseWeight = weight>desiredWeight;
		double actualStartWeight = weight;
		allcategories = new ArrayList<>();
		alldates = new ArrayList<>();
		expectedFinishDate = LocalDate.now();
		allcategories.add(expectedFinishDate.toString());
		alldates.add(actualStartWeight);
		while ((actualStartWeight>desiredWeight)||(actualStartWeight<desiredWeight&&!looseWeight)) {
			if(looseWeight) {
				actualStartWeight-=(actualStartWeight*0.0075);
			}
			else {
				actualStartWeight+=(actualStartWeight*0.0075);
			}
			expectedFinishDate = expectedFinishDate.plusWeeks(1);
			allcategories.add(expectedFinishDate.toString());
			alldates.add(actualStartWeight);
		}
	}
	
	public List<String> getAllcategories() {
		return allcategories;
	}
	public void setAllcategories(List<String> allcategories) {
		this.allcategories = allcategories;
	}
	public List<Double> getAlldates() {
		return alldates;
	}
	public void setAlldates(List<Double> alldates) {
		this.alldates = alldates;
	}
	public LocalDate getExpectedFinishDate() {
		return expectedFinishDate;
	}
	public void setExpectedFinishDate(LocalDate expectedFinishDate) {
		this.expectedFinishDate = expectedFinishDate;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getDesiredWeight() {
		return desiredWeight;
	}
	public void setDesiredWeight(double desiredWeight) {
		this.desiredWeight = desiredWeight;
	}
}
