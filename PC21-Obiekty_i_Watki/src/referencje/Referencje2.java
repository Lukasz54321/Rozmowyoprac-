package referencje;

public class Referencje2 {
	
	static void test(Konto aa, Konto bb, int xx) {
		System.out.println("Początek metody:");
		System.out.println("aa: " + aa);
		System.out.println("bb: " + bb);
		System.out.println("xx: " + xx);
		System.out.println();
		
		xx += 33;
		
		bb.wplata(40);
		bb.saldo += 8;
		
		aa = bb;
		
		System.out.println("Koniec metody:");
		System.out.println("aa: " + aa);
		System.out.println("bb: " + bb);
		System.out.println("xx: " + xx);
		System.out.println();
	}

	public static void main(String[] args) {
		Osoba ala = new Osoba("Ala", "Kowalska", 30);
		Osoba ola = new Osoba("Ola", "Malinowska", 40);

		Konto a = new Konto(1, 1000, ala);
		Konto b = new Konto(2, 2000, ola);
		int x = 5000;
		
		System.out.println("Początek main:");
		System.out.println(" a: " + a);
		System.out.println(" b: " + b);
		System.out.println(" x: " + x);
		System.out.println();
		
		test(a, b, x);
		
		System.out.println("Koniec main:");
		System.out.println(" a: " + a);
		System.out.println(" b: " + b);
		System.out.println(" x: " + x);
	}

}











