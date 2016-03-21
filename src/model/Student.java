package model;

public class Student {
	private String gebruikersNaam;
	private String wachtwoord;
	private Klas mijnKlas;

	public Student(String gebruikersNaam, String wachtwoord) {
		this.wachtwoord = wachtwoord;
		this.gebruikersNaam = gebruikersNaam;
	}

	public String getGebruikersNaam() {
		return gebruikersNaam;
	}

	public boolean controleerWachtwoord(String wachtwoord) {
		return wachtwoord.equals(this.wachtwoord);
	}

	public void setMijnKlas(Klas mijnKlas) {
		this.mijnKlas = mijnKlas;
	}

	public Klas getMijnKlas() {
		return mijnKlas;
	}
}
