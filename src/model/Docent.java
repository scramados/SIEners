package model;

import java.util.ArrayList;
import java.util.Objects;

public class Docent {
	private String gebruikersNaam;
	private String wachtwoord;
	private ArrayList<Vak> mijnVakken;
	private Rooster mijnRooster;

	public Docent(String gebruikersNaam, String wachtwoord) {
		mijnVakken = new ArrayList<>();
		this.gebruikersNaam = gebruikersNaam;
		this.wachtwoord = wachtwoord;
	}

	public Docent(String gebruikersNaam){
		mijnVakken = new ArrayList<>();
		this.gebruikersNaam = gebruikersNaam;
		wachtwoord = "test";
	}

	public void setRooster(Rooster mijnRooster) {
		this.mijnRooster = mijnRooster;
	}

	public Rooster getRooster() {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Docent)) return false;
		Docent docent = (Docent) o;
		return Objects.equals(gebruikersNaam, docent.gebruikersNaam) &&
				Objects.equals(wachtwoord, docent.wachtwoord) &&
				Objects.equals(mijnVakken, docent.mijnVakken) &&
				Objects.equals(mijnRooster, docent.mijnRooster);
	}

	@Override
	public int hashCode() {
		return Objects.hash(gebruikersNaam, wachtwoord, mijnVakken, mijnRooster);
	}

	@Override
	public String toString() {
		return gebruikersNaam;
	}
}
