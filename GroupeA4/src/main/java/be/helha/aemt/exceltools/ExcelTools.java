package be.helha.aemt.exceltools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.Part;
import javax.swing.JFileChooser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import be.helha.aemt.entities.ESection;

public class ExcelTools {
	
	//renvoie le nombre de ligne dans la feuille
	public int getLastRowWithData(Sheet sheet) 
	{
		
        int rowCount = 0;
        Iterator<Row> iter = sheet.rowIterator();
 
        while (iter.hasNext()) {
            Row r = iter.next();
            if (!this.isRowBlank(r)) {
                rowCount = r.getRowNum();
            }
        }
 
        return rowCount;
    }
	
	//verifie si la ligne est blank
    public boolean isRowBlank(Row r) {
        boolean ret = true;
 
       //si la row == null , elle est considéré comme blank
        if (r != null) {
            Iterator<Cell> cellIter = r.cellIterator();
            //parcours toutes les cellules de la ligne
            while (cellIter.hasNext()) {
                //prends la valeur si la cellule n'est pas vide
                if (!this.isCellBlank(cellIter.next())) {
                    ret = false;
                    break;
                }
            }
        }
 
        return ret;
    }
    
    //renvoie true si la cellule est blank
    public boolean isCellBlank(Cell c) {
        return (c == null || c.getCellTypeEnum() == CellType.BLANK);
    }
    
    //renvoie true si la cellule est vide
    public boolean isCellEmpty(Cell c) {
        return (c == null || c.getCellTypeEnum() == CellType.BLANK || (c
                .getCellTypeEnum() == CellType.STRING && c
                .getStringCellValue().isEmpty()));
    }

    public int findColumnWithName(Sheet sheet,String columnName) {
     Row titleRow =sheet.getRow(0);
    	int k=0;
    	 while(titleRow.getCell(k)==null || !titleRow.getCell(k).getStringCellValue().equals(columnName)) 
	        {
	        	k++;
	        }
    	 return k;
    }
    //split the string with the charactere
    public static String[] splitCell(String stringToSplit,String character) 
	{	
		String value[] = new String[2];
		int p = stringToSplit.indexOf(character);
		
		//si le stringToSplit est séparable alors on le sépare
		if(p>0) 
		{
			value[0] = stringToSplit.substring(0,p); //left value
			value[1] = stringToSplit.substring(p+1); //right value
		}
		else {
			value[0] = stringToSplit;
		}
		return value;
	}
    
    /**
     * on recupere le nom de la feuille depuis la ligne
     * 
     * @param row , la ligne sur laquelle on travail
     * @return le nom de la section 
     */
    public static String getSheetName(Row row) 
    {
	    String section = ESection.valueOf(row.getSheet().getSheetName()).getText();
	    return section;
    }
    
    
    /**
     * renvoie l'année académique
     * 
     * @return l'année académique
     */
    public static String getDate() 
    { 
    	ZoneId z = ZoneId.of("Europe/Paris"); 
    	LocalDate today = LocalDate.now(z); 
    	Year currentYear = Year.from(today); 
    	Year thisYear = currentYear.plusYears(-1); 
    	Year nextYear = currentYear.plusYears(0); 
    	String date = thisYear+"/"+nextYear;
       return date;
       
    }
	
    /**
     *
     *renvoie le nombre de credits en fonction de la ligne et de la colonne
     * 
     * @param row l'index de la ligne 
     * @param l'index de la colonne 
     * @return le nombre de credits
     */
    public static double getRowCredits(Row row,int col) 
	{
		double credit=0;
		Row rowCredits = row.getSheet().getRow(2);
		if(rowCredits.getCell(col)!=null)
			  credit=rowCredits.getCell(col).getNumericCellValue();
		return credit;
	}
    
    /**
     * Ouvre un fichier 
     */
    public static String openFile() {
    	
    	JFileChooser dialogue = new JFileChooser();
		PrintWriter sortie = null;
		File fichier=null;
		
		if (dialogue.showOpenDialog(null)== 
		    JFileChooser.APPROVE_OPTION) {
		    fichier = dialogue.getSelectedFile();
		    try {
				sortie = new PrintWriter
				(new FileWriter(fichier.getPath(), true));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    sortie.close();
		}
		return fichier.getPath();
    }
    public static String getFileNameFromPart(Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : partHeader.split(";")) {
			if (content.trim().startsWith("filename")) {
				String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
				return fileName;
			}
		}
		return null;
	}
    
}
