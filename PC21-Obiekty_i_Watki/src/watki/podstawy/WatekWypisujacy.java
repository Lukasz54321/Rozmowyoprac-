package watki.podstawy;

class WatekWypisujacy implements Runnable {
	private final String tekst;
	private final int iloscPowtorzen;
	private final int pauza;
	
	public WatekWypisujacy(String tekst, int iloscPowtorzen, int pauza) {
		this.tekst = tekst;
		this.iloscPowtorzen = iloscPowtorzen;
		this.pauza = pauza;
	}

	public void run() {
		System.out.println(tekst + " : start  wątku, nr " + Thread.currentThread().getId());
		try {
			for(int i = 1; i <= iloscPowtorzen; i++) {
				if(pauza > 0) {
					Thread.sleep(pauza);
				}
				System.out.println(tekst + " " + i);
			}
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		System.out.println(tekst + " : koniec wątku, nr " + Thread.currentThread().getId());		
	}
}
