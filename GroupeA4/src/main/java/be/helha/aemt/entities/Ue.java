package be.helha.aemt.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Ue implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<AA>activites;
	private String identification;
	private double numberOfCredits;
	private String label;
	private String AcademicYears;
	private String section;
	private String bloc;
	
	
	public Ue() {
		activites=new ArrayList<AA>();
	}
	
	public Ue(String section) {
		this.section=section;
	}
	
	public void addAA(AA aa) {
		activites.add(aa);
	}

	

	public List<AA> getActivites() {
		return activites;
	}

	public String getBloc() {
		return bloc;
	}

	
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumberOfCredits(double numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setAcademicYears(String academicYears) {
		AcademicYears = academicYears;
	}

	public void setBloc(String bloc) {
		this.bloc = bloc;
	}

	public void setActivites(List<AA> activites) {
		this.activites = activites;
	}




	public String getIdentification() {
		return identification;
	}




	public void setIdentification(String identification) {
		this.identification = identification;
	}




	public double getNombreDeCredits() {
		return numberOfCredits;
	}




	public void setNombreDeCredits(double nombreDeCredits) {
		this.numberOfCredits = nombreDeCredits;
	}




	public String getIntitule() {
		return label;
	}




	public void setIntitule(String intitule) {
		this.label = intitule;
	}




	public String getAnneeAcademique() {
		return AcademicYears;
	}




	public void setAnneeAcademique(String anneeAcademique) {
		this.AcademicYears = anneeAcademique;
	}




	public String getSection() {
		return section;
	}





	public Integer getId() {
		return id;
	}

	public double getNumberOfCredits() {
		return numberOfCredits;
	}

	public String getLabel() {
		return label;
	}

	public String getAcademicYears() {
		return AcademicYears;
	}

	public void setSection(String section) {
		this.section = section;
	}

	@Override
	public String toString() {
		return "Ue \n"
				+ "identification : "+  identification + "\n nombreDeCredits="
				+ numberOfCredits + "\n intitule=" + label + "\n anneeAcademique=" + AcademicYears + "\n section="
				+ section + "\n AA :"+activites.toString() ;
	}

	
	
	
	
	
}
