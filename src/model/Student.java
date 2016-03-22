package model;

public class Student {
	private String studentNummer;
	private String voorNaam, tussenVoegsel, achterNaam, wachtwoord;
	private Klas mijnKlas;

	public Student(String studentNummer, String wachtwoord, Klas mijnKlas, String voorNaam, String achterNaam, String tussenVoegsel) {
		this.studentNummer = studentNummer;
		this.voorNaam = voorNaam;
		this.tussenVoegsel = tussenVoegsel;
		this.achterNaam = achterNaam;
		this.wachtwoord = wachtwoord;
		this.mijnKlas = mijnKlas;
	}

	public Student(String studentNummer, String wachtwoord, Klas mijnKlas, String voorNaam, String achterNaam) {
		this.studentNummer = studentNummer;
		this.voorNaam = voorNaam;
		this.achterNaam = achterNaam;
		this.wachtwoord = wachtwoord;
		this.mijnKlas = mijnKlas;
	}

	public String getGebruikersNaam() {
		return studentNummer;
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
