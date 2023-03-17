package referencje;

public class ParametryString2 {
	
	static void zmien(StringBuilder napis) {
		napis.append(" ma kota");
		System.out.println(napis);
	}

	public static void main(String[] args) {
		StringBuilder napis = new StringBuilder("Ala");
		System.out.println(napis);
		zmien(napis);
		System.out.println(napis);
	}

}
