package be.helha.aemt.reader;

import java.io.File;
import java.util.List;

import be.helha.aemt.entities.Student;
import be.helha.aemt.entities.Ue;

public interface IExcelReaderStudent {
	
	List<Student>findAllStudent(String file,List<Ue>ues);

	
}
