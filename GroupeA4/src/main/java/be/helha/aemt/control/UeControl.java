package be.helha.aemt.control;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import be.helha.aemt.ejb.GestionUeEJB;
import be.helha.aemt.entities.AA;
import be.helha.aemt.entities.Ue;
@Named
@SessionScoped
public class UeControl implements Serializable {

	private static final long serialVersionUID = 1L;

		private Ue ue;
		private AA aa;
	
		@Inject
		private GestionUeEJB gestionUe;
		
		
		/**
		 * Retourne toutes les Ues
		 * 
		 * @return toutes les ues
		 */
		public List<Ue> selectAll(){
			return gestionUe.selectAll();
		
		}
		
		/**
		 * 
		 * Retourne toutes les sections
		 * 
		 * @return toutes les sections
		 */
		public List<String> selectSectionsFromUes(){
			
			return gestionUe.selectSections();
		}
		
		/**
		 * Retourne tous les blocs
		 * 
		 * @return les blocs 
		 */
		public List<String> selectBlocsFromUes(){
			return gestionUe.selectBlocsFromUes();
		}
		
		
		
		public List<AA> selectAAFromUe(Ue ue){
			return gestionUe.selectAAFromUe(ue);
		}
		
		
		/*----------------GETTER & SETTER ---------------- */
		

		public Ue getUe() {
			return ue;
		}

		public void setUe(Ue ue) {
			this.ue = ue;
		}

		public AA getAa() {
			return aa;
		}

		public GestionUeEJB getGestionUe() {
			return gestionUe;
		}

	
		
		
	
}
