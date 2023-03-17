package referencje;

public class Referencje1 {

	public static void main(String[] args) {
		Osoba ala = new Osoba("Ala", "Kowalska", 30);
		Osoba ola = new Osoba("Ola", "Malinowska", 40);
		
		Konto a = new Konto(1, 1000, ala);
		Konto b = new Konto(2, 2000, ola);
		Konto c = b;
		
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("c: " + c);
		System.out.println();
		
		b.wplata(48);
		
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("c: " + c);
		System.out.println();
		
//		b.wlasciciel = new Osoba("Jan", "Nowak", 40);
//		System.out.println("a: " + a);
//		System.out.println("b: " + b);
//		System.out.println("c: " + c);
//		System.out.println();
		
		b = a;
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("c: " + c);
		System.out.println();
		
		// Teraz wszystkie zmienne będą wskazywać na Konto nr 1,
		// a do Konto nr 2 nie ma w ogóle dojścia
		c = b;
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("c: " + c);
		System.out.println();
		
		a = null;
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("c: " + c);
		System.out.println();
		
		c = b = a;
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("c: " + c);
	}

}
