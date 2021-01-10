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
public class Student implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 
	private String lastName;
	private String firstname;
	private String groupClass;
	private String section;
	private String laNumber;
	private String year;
	private int validatedCredit;
	private int totalCredit;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<UeStudent> uesStudents;
	
	
	public Student() {
		uesStudents = new ArrayList<UeStudent>();
		
	}
	
	

	public Student(Integer id, String lastName, String name, String group, String section, String laNumber, String year,
			int validatedCredit, int totalCredit) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstname = name;
		this.groupClass = group;
		this.section = section;
		this.laNumber = laNumber;
		this.year = year;
		this.validatedCredit = validatedCredit;
		this.totalCredit = totalCredit;
		uesStudents = new ArrayList<UeStudent>();
	}



	public void addUeStudents(UeStudent ueStudent) {
		if(ueStudent!=null) {
			uesStudents.add(ueStudent);
		}
	}
	
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getName() {
		return firstname;
	}


	public void setName(String name) {
		this.firstname = name;
	}


	public String getGroup() {
		return groupClass;
	}


	public void setGroup(String group) {
		this.groupClass = group;
	}


	public String getSection() {
		return section;
	}


	public void setSection(String string) {
		this.section = string;
	}


	public String getLaNumber() {
		return laNumber;
	}


	public void setLaNumber(String laNumber) {
		this.laNumber = laNumber;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public int getValidatedCredit() {
		return validatedCredit;
	}


	public void setValidatedCredit(int validatedCredit) {
		this.validatedCredit = validatedCredit;
	}


	public int getTotalCredit() {
		return totalCredit;
	}


	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}




	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getGroupClass() {
		return groupClass;
	}



	public void setGroupClass(String groupClass) {
		this.groupClass = groupClass;
	}



	public List<UeStudent> getUesStudents() {
		return uesStudents;
	}



	public void setUesStudents(List<UeStudent> uesStudents) {
		this.uesStudents = uesStudents;
	}



	@Override
	public String toString() {
		String str = "Student : lastName=" + lastName + ", name=" + firstname + ", group=" + groupClass + ", section=" + section
				+ ", laNumber=" + laNumber + ", year=" + year + ", validatedCredit=" + validatedCredit
				+ ", totalCredit=" + totalCredit + ",ues=" ;
			for( UeStudent s : uesStudents) {
				str+=s.toString();
			}
			return str;
	}
}
