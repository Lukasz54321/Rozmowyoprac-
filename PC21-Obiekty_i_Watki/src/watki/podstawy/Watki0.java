package watki.podstawy;

public class Watki0 {

	public static void main(String[] args) {
		// Tworzę obiekt wątku
		Thread watek0 = new Thread();
		
		// Uruchamiam wątek, który nic nie robi. On się za moment zakończy.
		watek0.start();
		
		// Aby stworzyć wątek, który coś robi, najpierw tworzę instancję interfejsu Runnable
		Runnable tresc1 = new Runnable() {
			public void run() {
				System.out.println("wątek 1: start");
				for(int i = 1; i <= 1000; i++) {
					System.out.println("wątek 1 , i = " + i);
				}
				System.out.println("wątek 1: koniec");
			}
		};
		
		Thread watek1 = new Thread(tresc1);
		System.out.println("main: Wątek 1 przygotowany.");

		// Od Java 8 obiekty Runnable (czyli "tresc watku") mozna tworzyc za pomocą wyrazeń lambda
		Runnable tresc2 = () -> {
				System.out.println("wątek 2: start");
				for(int i = 1; i <= 1000; i++) {
					System.out.println("wątek 2 , i = " + i);
				}
				System.out.println("wątek 2: koniec");
		};
		
		Thread watek2 = new Thread(tresc2);
		System.out.println("main: Wątek 2 przygotowany.");
		
		System.out.println("Uruchamiam wątki");
		watek1.start();
		watek2.start();
		
		System.out.println("main: Wątki wystartowały");
		System.out.println("main: KONIEC");
	}

}
