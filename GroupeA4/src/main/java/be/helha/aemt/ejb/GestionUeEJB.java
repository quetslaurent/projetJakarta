package be.helha.aemt.ejb;

import java.io.OutputStream;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import be.helha.aemt.dao.UeDAO;
import be.helha.aemt.entities.AA;
import be.helha.aemt.entities.Ue;
import be.helha.aemt.reader.IExcelReaderUE;


@Stateless
public class GestionUeEJB {
	
	@EJB
	private UeDAO ueDao;
		
	/**
	 * Retourne toutes les Ues
	 * @return toutes les Ues
	 */
	public  List<Ue> selectAll(){
		
		return ueDao.findAllUe();
	}
	
	/**
	 * Retourne toutes les sections
	 * @return toutes les sections
	 */
	public List<String> selectSections() {
		return ueDao.findAllSections();
	}
	
	/**
	 * Retourne tous les blocs
	 * @return tous les blocs
	 */
	public List<String> selectBlocsFromUes(){
		return ueDao.findAllBlocs();
	}
	
	public List<AA>selectAAFromUe(Ue ue){
		return ueDao.findAllAa(ue);
	}
	
	public List<Ue> getUeFromExcel(String filess,IExcelReaderUE readerUe){
		return ueDao.getUeFromExcel(filess, readerUe);
	}


	
}
