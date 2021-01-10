package be.helha.aemt.manager;

import org.apache.poi.ss.usermodel.Row;

import be.helha.aemt.entities.Student;
import be.helha.aemt.exceltools.ExcelTools;


public class ManagerStudent {
	/**
	 *  creer un etudiant 
	 * @param row ,la ligne de la feuille excel
	 * @param lastcolumn , le numero de la derniere colonne de la feuille
	 * @return l'etudiant creer
	 */
	public static Student createStudent(Row row,int lastcolumn) 
	{
		Student student = new Student();
    	//on sépare le nom prenom dans un tableau 
    	String nameLastName[] = row.getCell(1).getStringCellValue().split(" ");
    	//attribution des valeurs respectives
    	student.setLastName(nameLastName[0]);
    	student.setName(nameLastName[1]);
    	student.setLaNumber(row.getCell(2).getStringCellValue());
    	student.setSection(row.getSheet().getSheetName());
    	student.setGroup(row.getCell(3).getStringCellValue());
    	student.setTotalCredit((int)row.getCell(lastcolumn).getNumericCellValue());
    	student.setYear(ExcelTools.getDate());
    	//verifie et recupère les credits réussis
    	if(row.getCell(lastcolumn-1)!=null) 
    	{
        	student.setValidatedCredit((int)row.getCell(lastcolumn-1).getNumericCellValue());
    	}
    	return student;
	}	


}
