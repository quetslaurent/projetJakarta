package be.helha.aemt.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import be.helha.aemt.dao.StudentDAO;
import be.helha.aemt.entities.Student;

@Stateless
public class GestionStudentEJB {
	@EJB
	private StudentDAO dao;
	
	public Student addStudent(Student student) {
		dao.add(student);
		return student;
	}

}
