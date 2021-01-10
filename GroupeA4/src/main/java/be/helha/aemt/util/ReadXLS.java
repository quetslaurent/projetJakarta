package be.helha.aemt.util;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JFileChooser;

import be.helha.aemt.entities.ESection;
import be.helha.aemt.entities.Student;
import be.helha.aemt.entities.Ue;
import be.helha.aemt.entities.UeStudent;
import be.helha.aemt.exceltools.IExcelFile;
import be.helha.aemt.reader.IExcelReaderStudent;
import be.helha.aemt.reader.IExcelReaderUE;
import be.helha.aemt.reader.StudentReader;
import be.helha.aemt.reader.UEReader;

public class ReadXLS {
    public static void main(String[] args) throws IOException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("GroupeA4");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    
    
    

  JFileChooser dialogue = new JFileChooser(new File("."));
    			PrintWriter sortie;
    			File fichier=null;
    			
    			if (dialogue.showOpenDialog(null)== 
    			    JFileChooser.APPROVE_OPTION) {
    			    fichier = dialogue.getSelectedFile();
    			    sortie = new PrintWriter
    				(new FileWriter(fichier.getPath(), true));
    			    sortie.close();
    			}
    	    
    			
 
  
    	IExcelReaderStudent studentReader = new StudentReader();
    IExcelReaderUE reader =  new UEReader();
    List<Ue>ues=reader.findAllUE(IExcelFile.FILE_NAME);
    List<Student>students=studentReader.findAllStudent(fichier.getPath(),ues);
    	//List<Student>students=studentReader.findAllStudent(IExcelFile.FILE_NAME);
   
    
    tx.begin();

    for(Ue s : ues) {
			//System.out.println(s.toString());
    
    		
	   em.persist(s);
	        
    	 
	} 
   

    	for(Student s : students) {
	     em.persist(s);
	} 
   
    tx.commit();
	em.close();
    emf.close(); 
    
   
    }
    
    
}
     	
    

