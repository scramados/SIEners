package model;

public class Vak {
	public String vakCode;
	public String vakNaam;

	public Vak(String vakCode, String vakNaam) {
		this.vakCode = vakCode;
		this.vakNaam = vakNaam;
	}

	public String getVakNaam() {
		return vakNaam;
	}
	
	public String getVakCode() {
		return vakCode;
	}
}
