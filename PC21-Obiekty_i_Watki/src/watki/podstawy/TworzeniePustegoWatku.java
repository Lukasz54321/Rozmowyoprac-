package watki.podstawy;

public class TworzeniePustegoWatku {

	public static void main(String[] args) {
		System.out.println("początek main");
		Thread th = new Thread();
		th.start();
		System.out.println("koniec main");
	}

}
