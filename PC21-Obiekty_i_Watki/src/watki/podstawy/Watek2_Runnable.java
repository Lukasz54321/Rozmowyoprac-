package watki.podstawy;

public class Watek2_Runnable implements Runnable {

	@Override
	public void run() {
		System.out.println("Startuje wątek 2. id = " + Thread.currentThread().getId());
		
		for(int i = 1; i <= 1000; i++) {
			System.out.println("Wątek 2: " + i);
		}
		
		System.out.println("Koniec wątek 2.");
	}

}
