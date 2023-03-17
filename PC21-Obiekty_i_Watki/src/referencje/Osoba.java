package referencje;

public class Osoba {
	String imie, nazwisko;
	int wiek;
	
	Osoba() {
	}
	
	Osoba(String imie, String nazwisko, int wiek) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.wiek = wiek;
	}
	
	void przedstawSie() {
		System.out.println("Nazywam się " + imie + " " + nazwisko + " i mam " + wiek + " lat.");
	}
	
	void urodziny() {
		wiek++;
	}
	
	boolean jestPelnoletnia() {
		return wiek >= 18;
	}
	
	public String toString() {
		return imie + " " + nazwisko + " (" + wiek + " l.)";
	}
	
}
