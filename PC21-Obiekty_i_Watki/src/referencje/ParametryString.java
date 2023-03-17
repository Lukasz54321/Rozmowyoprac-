package referencje;

public class ParametryString {
	
	static void zmien(String napis) {
		napis += " ma kota";
		System.out.println(napis);
	}

	public static void main(String[] args) {
		String napis = "Ala";
		System.out.println(napis);
		zmien(napis);
		System.out.println(napis);
	}

}
