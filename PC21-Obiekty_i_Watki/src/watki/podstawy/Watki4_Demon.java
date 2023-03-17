package watki.podstawy;

public class Watki4_Demon {

	public static void main(String[] args) {
		System.out.println("Początek main");
		Thread th1 = new Thread(new WatekWypisujacy("A", 100, 1));
		Thread th2 = new Thread(new WatekWypisujacy("B", 100, 1));
		Thread th3 = new Thread(new WatekWypisujacy("C", 100, 1));
		
		th1.setDaemon(true);
		th2.setDaemon(true);
		th3.setDaemon(true);

		th1.start();
		th2.start();
		th3.start();

		// setDaemon wywołane po start jest niepoprawne - kończy się wyjątkiem
		// th1.setDaemon(false);
		// th1.setDaemon(true);
		
		System.out.println("main: wątki uruchomione");
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
		}
		System.out.println("main: poczekałem sobie");
		
		System.out.println("koniec main");
		// Jeśli w działaniu pozostały wyłącznie wątki będące demonami, to proces jest kończony (i te wątki są "zabijane" w dowolnym momencie).
	}

}
