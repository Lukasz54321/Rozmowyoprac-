SELECT * FROM products;

SELECT * FROM orders o JOIN customers c ON c.email = o.customer_email;

SELECT * FROM orders o
	JOIN customers c ON c.email = o.customer_email
	JOIN order_products op USING(order_id)
	JOIN products p USING(product_id)
ORDER BY order_id, product_id;

