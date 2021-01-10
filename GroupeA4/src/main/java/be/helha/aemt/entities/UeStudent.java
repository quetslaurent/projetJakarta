package be.helha.aemt.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class UeStudent implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 
	private Ue ue;
	private String validated;
	
	public UeStudent(){
		
	}
	
	
	
	public UeStudent(Ue ue, String validated) {
		super();
		this.ue = ue;
		this.validated = validated;
		
		
	}
	

	public UeStudent(Ue ue) {
		super();
		this.ue = ue;
	}
	public Ue getUe() {
		return ue;
	}
	public void setUe(Ue ue) {
		this.ue = ue;
	}
	public String getValidated() {
		return validated;
	}
	public void setValidated(String validated) {
		this.validated = validated;
	}



	@Override
	public String toString() {
		return  "\n"+ue + ", validated= " + validated ;
	}
	
	
	

}
