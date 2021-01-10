package be.helha.aemt.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import be.helha.aemt.entities.AA;
import be.helha.aemt.entities.ESection;
import be.helha.aemt.entities.Ue;
import be.helha.aemt.entities.UeStudent;
import be.helha.aemt.exceltools.ExcelTools;
import be.helha.aemt.exceltools.IExcelFile;
import be.helha.aemt.manager.ManagerAA;
import be.helha.aemt.manager.ManagerUe;



public class UEReader implements IExcelReaderUE
{

	private ExcelTools excelTools = new ExcelTools();
	private List<Ue>listesUe=new ArrayList<>();

	@Override
	public List<Ue> findAllUE(String file_directory) 
	{
		
		//initialisation 
		List<Ue>listesUes=new ArrayList<>();
		int lastColumn=0;
		final File file = new File(file_directory);
		
		//parcours des pages pour lire tout le excel  
		for(int page=0;page<=2;page++) 
		{
			try 
			{
				//creer un workbook  dans le java similaire au fichier excel ouvert
				Workbook workbook = WorkbookFactory.create(file);
				final Sheet sheet = workbook.getSheetAt(page);
				
				//initialisation des lignes 
		        Row row = sheet.getRow(1);
		        Row rowTitle = sheet.getRow(0);
		        
		        //obtient la dernière colonne 
		        lastColumn = excelTools.findColumnWithName(sheet, IExcelFile.LAST_COLUMN);
		        
		        //parcours les colonnes
		        for(int i =4;i<=lastColumn-3;i++) 
		        {	
		        	//si la cellule n'est pas blank
		        	if(!excelTools.isCellBlank(row.getCell(i))) 
		        	{	
		        		String value = row.getCell(i).getStringCellValue();
		        		
		        		//si la valeur de la cellule n'est pas une ACAP c'est que c'est une UE
		        		if(!value.equals(IExcelFile.ACAP)) 
		        		{
		        			Ue ue = ManagerUe.createUe(rowTitle, i);
		        			listesUes.add(ue);	
		        		}
		        		
		        		//si la valeur de la cellule est une ACAP c'est que c'est une ACAP 
		        		if(value.equals(IExcelFile.ACAP))
		        		{
		        			//cree une AA
		        			AA aa = ManagerAA.createAA(rowTitle, i);
		        			ManagerAA.addAA(listesUes.get(listesUes.size()-1),aa);
		        		}
		        	}
		        }
			}
			catch (EncryptedDocumentException  | IOException  e)
			{
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
		this.listesUe = listesUes;
		return listesUes;
	}
	
	/**
	 * renvoies une liste d'UeStudent depuis la ligne et de la feuille 
	 * 
	 * @param row , la ligne de l'etudiant dans le workbook
	 * @param lastColumn , la derniere colonne du workbook
	 * @param sheet , sheet est la feuille workbook
	 * @param ues , la liste d'ues que l'on a lu 
	 * @return une liste d'UeStudent  
	 */
	public List<UeStudent> findUeForStudent(Row row,int lastColumn,Sheet sheet,List<Ue>ues) {
		
		//initialisation des différents elements utilisés
		Row rowTitle = sheet.getRow(0);
		Row rowAcap = sheet.getRow(1);
		
		List<UeStudent>listesUeStudent=new ArrayList<>();
		
		//increment l'index de la colonne 
		for(int col=4;col<=lastColumn-3;col++) 
		{

			//si la case n'est pas blanche ou vide
			if(!excelTools.isCellBlank(rowAcap.getCell(col)) || !excelTools.isCellEmpty(rowAcap.getCell(col))) 
			{
				int colValidated = col+1;
				if(!excelTools.isCellBlank(row.getCell(colValidated)) || !excelTools.isCellEmpty(row.getCell(colValidated))) 
				{	
					//verifie si c'est une UE 
					if(!rowAcap.getCell(col).getStringCellValue().equals(IExcelFile.ACAP)&&!rowAcap.getCell(col).getStringCellValue().equals(IExcelFile.EPIN)) 
					{
						//verifie si l'identification est correct par rapport a la valeur de rowtitle
						String identification = rowTitle.getCell(col).getStringCellValue();
						Ue ue  = findUeByIdentification(ExcelTools.splitCell(identification, ":")[0],ues);
			
						//Creation de l'ueStudent 
						UeStudent ueStudent = new UeStudent(ue);
						
						//definit si il a reussit le cours ou pas 
						ueStudent.setValidated(row.getCell(colValidated).getStringCellValue());
						
						//ajoute a la liste de UEStudent
						listesUeStudent.add(ueStudent);
					}
				}
			}
		}
		
		
		return listesUeStudent;
	}
	
	
	
	
	/**
	 * renvoie l'ue avec l'identification correspondant si pas il renvoie un null
	 * @param identification , le numero d'identification
	 * @return
	 */
	public Ue findUeByIdentification(String identification,List<Ue>listesUe) {
		for(Ue ue : listesUe) {
			if(ue.getIdentification().equals(identification)) {
				return ue;
			}
		}
		return null;
	}

}
