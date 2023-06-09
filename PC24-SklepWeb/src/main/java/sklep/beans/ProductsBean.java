package sklep.beans;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import sklep.db.DBConnection;
import sklep.db.DBException;
import sklep.db.ProductDAO;
import sklep.db.SklepException;
import sklep.model.Product;

public class ProductsBean {
	private BigDecimal minPrice, maxPrice;

	// Chociaż wewnętrznie zmienna jest typu BigDecimal, to gettery isettery napiszemy tak, jakby to były Stringi.
	// Robimy to po to, aby w JSP zadziałało setProperty.
	
	public String getMinPrice() {
		return minPrice == null ? null : minPrice.toString();
	}

	public void setMinPrice(String minPrice) {
		if(minPrice == null || minPrice.isEmpty()) {
			this.minPrice = null;
		} else {
			this.minPrice = new BigDecimal(minPrice);
		}
	}
	
	public String getMaxPrice() {
		return maxPrice == null ? null : maxPrice.toString();
	}

	public void setMaxPrice(String maxPrice) {
		if(maxPrice == null || maxPrice.isEmpty()) {
			this.maxPrice = null;
		} else {
			this.maxPrice = new BigDecimal(maxPrice);
		}
	}

	public List<Product> getAllProducts() {
		try(DBConnection db = DBConnection.open()) {
			return db.productDAO().readAll();
		} catch (DBException e) {
			e.printStackTrace();
			return List.of();
		}
	}

	public List<Product> getProductsByPrice() {
		try(DBConnection db = DBConnection.open()) {
			ProductDAO productDAO = db.productDAO();
			List<Product> products = productDAO.findByPrice(minPrice, maxPrice);
			return products;
		} catch (SklepException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
}
