package sklep.koszyk;

import java.math.BigDecimal;

public class ElementKoszyka {
	private int productId;
	private String productName;
	private BigDecimal price;
	private int quantity;

	public ElementKoszyka(int productId, String name, BigDecimal price, int quantity) {
		this.productId = productId;
		this.productName = name;
		this.price = price;
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getValue() {
		return price.multiply(BigDecimal.valueOf(quantity));
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void increaseQuantity(int quantity) {
		this.quantity += quantity;
	}

	@Override
	public String toString() {
		return "ElementKoszyka [productId=" + productId + ", name=" + productName + ", price=" + price + ", quantity="	+ quantity + "]";
	}	
}
