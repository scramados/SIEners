package model;

public class Vak {
	public String vakCode;
	public String vakNaam;
	
	public Vak(String vC, String vN) {
		vakCode = vC;
		vakNaam = vN;
	}
	
	public String getVakNaam() {
		return vakNaam;
	}
	
	public String getVakCode() {
		return vakCode;
	}
}
