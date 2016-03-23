package model;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Vak)) return false;
		Vak vak = (Vak) o;
		return Objects.equals(vakCode, vak.vakCode) &&
				Objects.equals(vakNaam, vak.vakNaam);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vakCode, vakNaam);
	}
}
