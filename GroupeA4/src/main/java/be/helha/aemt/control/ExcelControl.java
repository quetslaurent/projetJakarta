package be.helha.aemt.control;


import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import be.helha.aemt.ejb.GestionUeEJB;
import be.helha.aemt.reader.IExcelReaderUE;

@Named
@SessionScoped
public class ExcelControl implements Serializable {
	
	private String content;
	private IExcelReaderUE excelReader;
	
	@Inject 
	private GestionUeEJB gestionUe;
	
	public void getUeFromExcel()  {
		gestionUe.getUeFromExcel(content, excelReader);
	}
	
	public GestionUeEJB getGestionUe() {
		return gestionUe;
	}
	public IExcelReaderUE getExcelReader() {
		return excelReader;
	}
	public void setExcelReader(IExcelReaderUE excelReader) {
		this.excelReader = excelReader;
	}
	public void setGestionUe(GestionUeEJB gestionUe) {
		this.gestionUe = gestionUe;
	}
	
	
	
}
	
	

