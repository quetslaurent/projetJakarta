package be.helha.aemt.dao;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import be.helha.aemt.entities.Student;

@Stateless
public class StudentDAO 
{
	
	@PersistenceContext(unitName = "GroupeA4JTA")
	private EntityManager em;
	
		
	public Student add(Student student) 
	{
		em.persist(student);
		return student;
	}
	
	
	
	
}
