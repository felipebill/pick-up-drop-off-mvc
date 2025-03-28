package dev.felipebill.pudo.model;

public enum Country {

	BRASIL("BR");
	
	private final String acronym;
	
	Country(final String acronym){
		this.acronym = acronym;
	}
	
	public String getAcronym() {
		return this.acronym;
	}
	
}
