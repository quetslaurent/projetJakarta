package be.helha.aemt.entities;

public enum ESection {
	IG("Informatique de gestion"),
	CT("Comptabilite"),
	AD("Assistante de direction");

	private String text;
	
	private ESection(String text) {
		this.text=text;
	}
	
	public String getText() {
		return this.text;
	}

}
