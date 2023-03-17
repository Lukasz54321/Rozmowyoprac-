package watki.podstawy;


public class Watki1 {

	public static void main(String[] args) {
		System.out.println("Początek main, nr wątku " + Thread.currentThread().getId());

		Thread watek1 = new Thread(new WatekA());
		WatekB przepisB = new WatekB();
		Thread watek2 = new Thread(przepisB);
		Thread watek3 = new Thread(przepisB); // wiele wątków może korzystać z tego samego Runnable
		
		watek1.start();
		watek2.start();
		watek3.start();
		System.out.println("Wątki odpalone");
	}

	// Obiekt klasy implementującej Runnable jest przepisem mówiącym "co ma robić wątek".
	private static class WatekA implements Runnable {
		@Override
		public void run() {
			System.out.println("Wątek A start, nr wątku " + Thread.currentThread().getId());
			for(int i = 1; i < 1000; i++) {
				System.out.println("A");
			}
			System.out.println("Wątek A koniec");
		}
	}

	private static class WatekB implements Runnable {
		@Override
		public void run() {
			System.out.println("Wątek B start, nr wątku " + Thread.currentThread().getId());
			for(int i = 1; i < 1000; i++) {
				System.out.println(" B");
			}
			System.out.println("Wątek B koniec");
		}
	}
}
