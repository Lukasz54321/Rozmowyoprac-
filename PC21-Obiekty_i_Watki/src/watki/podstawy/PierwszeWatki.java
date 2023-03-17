package watki.podstawy;

public class PierwszeWatki {

	public static void main(String[] args) {
		System.out.println("Początek main");
		
		// Pierwszy wątek za pomocą "klasy anonimowej" - ten zapis dostępny od b. dawna
		Thread watekA = new Thread(new Runnable() {
			public void run() {
				System.out.println("Początek A, nr wątku: " + Thread.currentThread().getId());
				for(int i = 1; i <= 100; i++) {
					System.out.println("AAA " + i);
				}
				System.out.println("Koniec A");				
			}
		});
		
		// Drugi wątek utworzymy za pomocą wyrażenia lambda (od Javy 8)
		Thread watekB = new Thread(() -> {
			System.out.println("Początek B, nr wątku: " + Thread.currentThread().getId());
			for(int i = 1; i <= 100; i++) {
				System.out.println("BBB " + i);
			}
			System.out.println("Koniec B");				
		});
		
		watekA.start();
		watekB.start();
		// gdybyśmy napisali .run() , to metoda zostałaby wykonana przez wątek main
		
		System.out.println("Koniec main");
	}

}





