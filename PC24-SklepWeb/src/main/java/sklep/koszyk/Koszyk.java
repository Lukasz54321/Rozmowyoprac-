package sklep.koszyk;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import sklep.model.Product;

public class Koszyk {
	private Map<Integer, ElementKoszyka> elementy = new LinkedHashMap<>();
	
	public void dodajProdukt(Product product, int quantity) {
		final Integer productId = product.getProductId();
		if(elementy.containsKey(productId)) {
			ElementKoszyka element = elementy.get(productId);
			element.increaseQuantity(quantity);
			// nie trzeba robić put, bo ten obiekt już jest w mapie
		} else {
			ElementKoszyka element = new ElementKoszyka(productId,
					product.getProductName(), product.getPrice(), quantity);
			elementy.put(productId, element);
		}
	}
	
	public Collection<ElementKoszyka> getElements() {
		return elementy.values();
	}
	
	public BigDecimal getTotalValue() {
		return getElements().stream()
			.map(ElementKoszyka::getValue)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	@Override
	public String toString() {
		return "Koszyk o rozmiarze " + elementy.size();
	}

}
