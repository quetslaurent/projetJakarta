package be.helha.aemt.manager;

import org.apache.poi.ss.usermodel.Row;

import be.helha.aemt.entities.AA;
import be.helha.aemt.entities.Student;
import be.helha.aemt.entities.Ue;
import be.helha.aemt.exceltools.ExcelTools;


public class ManagerAA {
	
	/**
	 * ajoute une AA a une Ue
	 * 
	 * @param ue a laquelle on veut ajouter l'AA 
	 * @param aa que l'on ajoute à l'ue
	 */
	public static void addAA(Ue ue,AA aa) 
	{
		ue.addAA(aa);
	}
	
	
	/**
	 * 
	 * Creer une AA
	 * @param row , la ligne de la feuille excel
	 * @param col , l'index de la colonne
	 * @return l'AA creer
	 */
	public static AA createAA(Row row,int col) 
	{
		AA aa = new AA();
		aa.setName(row.getCell(col).getStringCellValue());
		aa.setSection(ExcelTools.getSheetName(row));
		aa.setNumbersOfCredits(ExcelTools.getRowCredits(row, col)); 
		return aa;
	}
}
