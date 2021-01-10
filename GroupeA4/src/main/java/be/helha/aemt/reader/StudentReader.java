package be.helha.aemt.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import be.helha.aemt.entities.ESection;
import be.helha.aemt.entities.Student;
import be.helha.aemt.entities.Ue;
import be.helha.aemt.entities.UeStudent;
import be.helha.aemt.exceltools.ExcelTools;
import be.helha.aemt.manager.ManagerStudent;

public class StudentReader implements IExcelReaderStudent {
	
	
	private UEReader readerUe = new UEReader();
	final List<Student> students = new ArrayList<>();
	ExcelTools excelTools = new ExcelTools();
	int lastcolumn;
	int lastRow;

	@Override
	/**
	 * renvoie une liste d'etudiants avec les cours qui leurs sont attribués
	 * @param file_directory chemin vers le fichier excel
	 * @param ues , listes d'UE prealabement lu 
	 * 
	 * return liste d'étudiants
	 * 
	 */
	public List<Student> findAllStudent(String file_directory,List<Ue>ues) 
	{
		final File file = new File(file_directory);
		
		//parcours le numero de la page
		for(int page=0;page<=2;page++) 
		{	
			try 
			{
				//creer un workbook  dans le java similaire au fichier excel ouvert
				Workbook workbook = WorkbookFactory.create(file);
				final Sheet sheet = workbook.getSheetAt(page);
		        Row row = sheet.getRow(3);
		        
		        //recherche la derniere colonne
		        lastcolumn = excelTools.findColumnWithName(sheet,"Crédits Totaux");
		    	lastRow = excelTools.getLastRowWithData(sheet);
		    	
		        //creation d'un objet etudiant via l'excel 
		        for(int i=3;i<=lastRow;i++) 
		        {   
		        	row = sheet.getRow(i);
		        	if(!row.getCell(1).getStringCellValue().equals("")) 
		        	{
		        		//creer une liste d'UeStudent qui fait le lien entre les Ue et les Etudiants
		        		List<UeStudent> uesStudents= readerUe.findUeForStudent(row, lastcolumn,sheet,ues);
		        		
		        		//creer un etudiant
		        		Student student = ManagerStudent.createStudent(row, lastcolumn);
		        		
			        	//ajoute l'etudiant a la liste et passe à la ligne suivante
		        		student.setUesStudents(uesStudents);
				        students.add(student); 
		        	}
		        	  
		        }  
			}
			catch (EncryptedDocumentException  | IOException  e)
			{
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
		    return students;
	}

}