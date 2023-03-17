package watki.podstawy;

public class Watek1_Extends extends Thread {
	// Możemy określić własną treść - "co ma robić wątek" - nadpisując metodę run()
	
	@Override
	public void run() {
		System.out.println("Startuje wątek 1. id = " + Thread.currentThread().getId());
		
		for(int i = 1; i <= 1000; i++) {
			System.out.println("Wątek 1: " + i);
		}
		
		System.out.println("Koniec wątek 1.");
	}
}
