package watki.podstawy;

public class Watki3_Join {

	public static void main(String[] args) {
		System.out.println("Początek main");
		Thread th1 = new Thread(new WatekWypisujacy("A", 100, 1));
		Thread th2 = new Thread(new WatekWypisujacy("B", 100, 1));
		Thread th3 = new Thread(new WatekWypisujacy("C", 100, 1));

		System.out.println("stan przed start: " + th1.getState());
		
		th1.start();
		th2.start();
		th3.start();
		System.out.println("main: wątki uruchomione, mój nr " + Thread.currentThread().getId());
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
		}
		System.out.println("main: Teraz będę czekał na wątki za pomocą join");
		
		System.out.println("stan przed join: " + th1.getState());
		
		// wątek main czeka na zakończenie wątków th1, th2, th3
		// jeśli one się skończyły wcześniej, to od razu przechodzi dalej
		try {
			th1.join();
			th2.join();
			th3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("main: doczekałem się na zakończenie wątków");
		System.out.println("stan po join: " + th1.getState());
		System.out.println("koniec main");
	}

}
