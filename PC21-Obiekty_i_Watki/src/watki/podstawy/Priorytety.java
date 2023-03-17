package watki.podstawy;

import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Priorytety {
	private final int N;
	private final int T;
	private volatile boolean koniec = false;
	private final AtomicLongArray tablica;
	private final AtomicReferenceArray<Thread> watki;

	public Priorytety(int n, int t) {
		this.N = n;
		this.T = t;
		this.tablica = new AtomicLongArray(n);
		this.watki = new AtomicReferenceArray<>(n);
	}

	private class Robotnik implements Runnable {
		private final int numer;
		
		Robotnik(int numer) {
			this.numer = numer;
		}

		public void run() {
			while(!koniec) {
				tablica.incrementAndGet(numer);
			}
		}
	}
	
	private void run() {
		final int polowa = N / 2;
		for(int i = 0; i < N; i++) {
			Thread th = new Thread(new Robotnik(i));
			if(i < polowa) {
				th.setPriority(Thread.MAX_PRIORITY);
			} else {
				th.setPriority(Thread.MIN_PRIORITY);
			}
			watki.set(i, th);
		}
		
		for(int i = 0; i < N; i++) {
			watki.get(i).start();
		}
		
		try {
			Thread.sleep(1000 * T);
			koniec = true;
			for(int i = 0; i < N; i++) {
				watki.get(i).join();
			}
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		
		for(int i = 0; i < N; i++) {
			if(i == polowa) {
				System.out.println("================================");
			}
			long razy = tablica.get(i);
			System.out.printf("%2d : %15d razy\n", i, razy);
		}
	}

	public static void main(String[] args) {
		// domyślne wartości parametrów: ilość wątków, czas trwania testu
		int N = 32;
		int T = 10;
		
		// inne można przekazać w cmd-line
		if(args.length >= 1) {
			N = Integer.parseInt(args[0]);
		}
		if(args.length >= 2) {
			T = Integer.parseInt(args[1]);
		}
		
		System.out.printf("Startujemy %d wątków, czekaj %d sekund...\n", N, T);
		
		new Priorytety(N, T).run();
		
		System.out.println("Koniec");
	}

}
