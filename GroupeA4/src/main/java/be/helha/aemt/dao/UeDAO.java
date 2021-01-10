package be.helha.aemt.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import be.helha.aemt.entities.AA;
import be.helha.aemt.entities.Ue;
import be.helha.aemt.reader.IExcelReaderStudent;
import be.helha.aemt.reader.IExcelReaderUE;


@Stateless
public class UeDAO {
	//permet d'obtenir l'EM a partir du server => new 
	@PersistenceContext(unitName = "GroupeA4JTA")
	
	//accès à la base de données
	private EntityManager em;
	
	
	/**
	 * retourne toutes les ues
	 * 
	 * @return toutes les ues
	 */
	public List<Ue> findAllUe(){
		return em.createQuery("SELECT ue FROM Ue ue WHERE ue.section like 'Comptabilite' ").getResultList();
	}
	

	/**
	 * retourne toutes les sections
	 * 
	 * @return toutes les sections
	 */
	public List<String> findAllSections() {
		
		return em.createQuery("SELECT DISTINCT ue.section FROM Ue ue ").getResultList();
	}
	
	/**
	 * retourne tous les blocs
	 * 
	 * @return tous les blocs
	 */
	public List<String> findAllBlocs() {
		
		return em.createQuery("SELECT DISTINCT ue.bloc FROM Ue ue ORDER BY ue.bloc").getResultList();
	}

	public List<Ue> getUeFromExcel(String filePath,IExcelReaderUE readerUe){
		
		return readerUe.findAllUE(filePath);
		
	}
	
	
	
	public List<AA> findAllAa(Ue ue){
		
	String request = 	"SELECT ue.activites FROM Ue ue WHERE ue.section like 'Comptabilite' AND ue.id =?1";
	TypedQuery<AA> query = em.createQuery(request,AA.class);
	query.setParameter(1, ue.getId());
		
	return 	query.getResultList();
			
	}

}