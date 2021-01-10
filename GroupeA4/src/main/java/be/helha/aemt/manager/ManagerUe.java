package be.helha.aemt.manager;

import org.apache.poi.ss.usermodel.Row;

import be.helha.aemt.entities.Ue;
import be.helha.aemt.exceltools.ExcelTools;


public class ManagerUe {
	/**
	 * Creer une Ue a partir d'une ligne et d'une colonne
	 *  
	 * @param row , la ligne de la feuille excel
	 * @param col , l'index de la colonne de la feuille excel 
	 * @return une Ue creer
	 */
	public static Ue createUe(Row row,int col) 
	{
		Ue ue = new Ue();
		String idName[]=ExcelTools.splitCell(row.getCell(col).getStringCellValue(),":");
		ue.setIdentification(idName[0]);
		ue.setIntitule(idName[1]);
		ue.setSection(ExcelTools.getSheetName(row));
		ue.setNombreDeCredits(ExcelTools.getRowCredits(row, col));
		ue.setAnneeAcademique(ExcelTools.getDate());
		int number = row.getRowNum();
		Row rowBloc = row.getSheet().getRow(number+1);
	
		String bloc[]=ExcelTools.splitCell(rowBloc.getCell(col).getStringCellValue(), " ");
		
		System.out.println(col + " " + bloc.length );
		if(bloc[1]!= null) {
			ue.setBloc(bloc[1]);
			
		}
		else {
			ue.setBloc(bloc[0]);
		}
		
		return ue;
	}
	

}
