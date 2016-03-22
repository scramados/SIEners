package model;

public class Klas {
	private String klasCode;

	public Klas(String klasCode) {
		this.klasCode = klasCode;
	}

	public String getKlasCode() {
		return klasCode;
	}

	@Override
	public String toString() {
		return "Klas{" +
				"klasCode='" + klasCode + '\'' +
				'}';
	}
}
