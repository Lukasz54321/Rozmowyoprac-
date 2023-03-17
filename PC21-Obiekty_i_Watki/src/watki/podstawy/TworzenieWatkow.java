package watki.podstawy;

public class TworzenieWatkow {
	public static void main(String[] args) {
		System.out.println("Początek main, id wątku = " + Thread.currentThread().getId());
		
		// Jak tworzyć nowe wątki?
		// 1) dziedziczenie z klasy Thread:
		
		class Watek0 extends Thread {
		}
		
		class Watek1 extends Thread {
			// nadpisując metodę run podajemy treść wątku - instrukcje, które będzie on wykonywał
			@Override
			public void run() {
				System.out.println("Watek1 początek, id = " + Thread.currentThread().getId());
				for(int i = 1; i <= 10; i++) {
					System.out.println("Watek1 działa, id = " + Thread.currentThread().getId() + ", i = " + i);
				}
				System.out.println("Watek1 koniec, id = " + Thread.currentThread().getId());
			}
		}
		
		Watek1 watek1a = new Watek1();
		// wątek jeszcze nie wystartował
		
		// aby wątek zaczął działać, należy wywołać jego metodę
		watek1a.start();
		
		// gdybym wpisał watek1a.run(), to treść run też by się wykonała,
		// ale w jednym wątku main ("synchronicznie")
		
		Watek1 watek1b = new Watek1();
		watek1b.start();
		
		// 2) Implementacja interfejsu Runnable i utworzenie obiektu Thread na jej podstawie
		
		class Watek2 implements Runnable {
			public void run() {
				System.out.println("Watek2 początek, id = " + Thread.currentThread().getId());
				for(int i = 1; i <= 10; i++) {
					System.out.println("Watek2 działa, id = " + Thread.currentThread().getId() + ", i = " + i);
				}
				System.out.println("Watek2 koniec, id = " + Thread.currentThread().getId());
			}
		}

		Watek2 watek2_instancja_runnable = new Watek2();
		// to nie jest jeszcze wątek - bo nie dziedziliśmy z Thread
		// watek2_instancja_runnable.start();
		
		Thread watek2a = new Thread(watek2_instancja_runnable);
		// wiele wątków opartych o ten sam obiekt Runnable? - OK
		Thread watek2b = new Thread(watek2_instancja_runnable);
		
		watek2a.start();
		watek2b.start();
		
		Thread watek2c = new Thread(new Watek2());
		watek2c.start();
		
		// Zwięzłe zapisy: klasa anonimowa, wyrażenie lambda
		Thread watek3a = new Thread(new Runnable() {
			public void run() {
				System.out.println("wątek 3 a");
			}
		});
		watek3a.start();

		Thread watek3b = new Thread(() -> {
				System.out.println("wątek 3 b");
			});
		watek3b.start();
		
		new Thread(() -> {
				System.out.println("wątek 3 c");
			}).start();
		
		System.out.println("Koniec main");
	}
}
