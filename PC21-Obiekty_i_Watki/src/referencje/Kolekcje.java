package referencje;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Kolekcje {

	public static void main(String[] args) {
		// W kolekcjach przechowywane są referencje do obiektów, a nie ich kopie.
		
		List<String> miasta = new ArrayList<>(List.of("Warszawa", "Kraków", "Poznań"));
		List<String> imiona = new ArrayList<>(List.of("Ala", "Ola", "Ela"));
		
		Map<String, List<String>> mapa = new LinkedHashMap<>();
		mapa.put("osoby", imiona);
		mapa.put("miasta", miasta);
		mapa.put("nowa", new ArrayList<>(List.of("Java", "Python")));
		mapa.put("dodatek", imiona);
		
		System.out.println(mapa);
		System.out.println();
		
		imiona.add("Karolina");
		// nowy element listy będzie widoczny także poprzez mapę
		System.out.println(mapa);
		System.out.println();
		
		mapa.get("osoby").remove("Ola");
		System.out.println(mapa);
		System.out.println(imiona);
		System.out.println();
		
		List<String> jezyki = mapa.get("nowa");
		System.out.println(jezyki);
		
		mapa.get("nowa").replaceAll(String::toUpperCase);
		System.out.println(mapa);
		System.out.println(jezyki);
	}

}






