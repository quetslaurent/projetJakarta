package be.helha.aemt.reader;

import java.util.List;

import be.helha.aemt.entities.Ue;


public interface IExcelReaderUE {
	
	List<Ue>findAllUE(String file_directory);
}
