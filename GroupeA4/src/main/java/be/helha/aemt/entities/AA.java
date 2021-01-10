package be.helha.aemt.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AA implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private double numbersOfCredits;
	private String section;
	
	
	public AA() {
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public double getNumbersOfCredits() {
		return numbersOfCredits;
	}




	public void setNumbersOfCredits(double numbersOfCredits) {
		this.numbersOfCredits = numbersOfCredits;
	}




	public String getSection() {
		return section;
	}


	public void setSection(String string) {
		this.section = string;
	}

	
	

	@Override
	public String toString() {
		return "\n\t intitule=" + name + "\n\t nombreDeCredit=" + numbersOfCredits + "\n\t section=" + section+"\n";
	}
	
	
}
