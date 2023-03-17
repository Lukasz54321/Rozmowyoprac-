package watki.podstawy;

public class Watki2 {

	public static void main(String[] args) {
		System.out.println("Początek main");
		Thread th1 = new Thread(new WatekWypisujacy("A", 100, 1));
		Thread th2 = new Thread(new WatekWypisujacy("B", 100, 1));
		Thread th3 = new Thread(new WatekWypisujacy("C", 100, 1));

		th1.start();
		th2.start();
		th3.start();
		System.out.println("main: wątki uruchomione");
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
		}
		System.out.println("main: poczekałem sobie");
		
		System.out.println("koniec main");
		// main dochodzi do końca, a wątki działają dalej
	}

}
