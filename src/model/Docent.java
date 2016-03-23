package model;

import java.util.ArrayList;

public class Docent {
	private String gebruikersNaam;
	private String wachtwoord;
	private ArrayList<Vak> mijnVakken;
	private Rooster mijnRooster;

	public Docent(String gebruikersNaam, String wachtwoord) {
		mijnVakken = new ArrayList<Vak>();
		this.gebruikersNaam = gebruikersNaam;
		this.wachtwoord = wachtwoord;
	}
	public void setRooster(Rooster mijnRooster){
		this.mijnRooster=mijnRooster;
	}
	public Rooster getRooster(){
		return mijnRooster;
	}

	public String getGebruikersNaam() {
		return gebruikersNaam;
	}

	public boolean controleerWachtwoord(String wachtwoord) {
		return wachtwoord.equals(this.wachtwoord);
	}

	public void voegVakToe(Vak nieuwvak) {
		mijnVakken.add(nieuwvak);
	}
	
	public ArrayList<Vak> getVakken() {
		return mijnVakken;
	}
}
