INSERT INTO products(product_id, product_name, price, description)
	VALUES (1, 'pralka', 2900.00, 'Pralka szybkoobrotowa');

INSERT INTO products(product_id, product_name, price, description)
	VALUES (2, 'odkurzacz', 800.00, 'Odkurzacz automatyczny');

INSERT INTO products(product_id, product_name, price, description)
	VALUES (3, 'telewizor 55"', 3300.00, 'Telewizor 55 cali 4K');

INSERT INTO products(product_id, product_name, price, description)
	VALUES (4, 'telewizor 40"', 2200.00, 'Telewizor 40 Full HD');

INSERT INTO products(product_id, product_name, price)
	VALUES (5, 'myszka gejmerska', 444.00);

	
INSERT INTO customers(email, phone, customer_name, address, postal_code, city)
	VALUES ('ala@example.com', '123123123', 'Ala Kowalska', 'Jasna 14/16', '01-234', 'Warszawa');

INSERT INTO customers(email, phone, customer_name, address, postal_code, city)
	VALUES ('ola@example.com', '321321321', 'Ola Malinowska', 'Ciemna 133', '99-999', 'Pcim');


INSERT INTO orders(order_id, customer_email, order_date, order_status)
	VALUES (101, 'ala@example.com', '2021-11-20 12:30:00', 'paid');

INSERT INTO orders(order_id, customer_email, order_date, order_status)
	VALUES (102, 'ola@example.com', '2021-11-18 10:00:00', 'shipped');

INSERT INTO orders(order_id, customer_email)
	VALUES (103, 'ala@example.com');


INSERT INTO order_products(order_id, product_id, quantity, actual_price) 
	VALUES (101, 1, 1, 2900.00);
INSERT INTO order_products(order_id, product_id, quantity, actual_price) 
	VALUES (101, 2, 3, 2400.00);
INSERT INTO order_products(order_id, product_id, quantity, actual_price) 
	VALUES (102, 2, 1, 800.00);
INSERT INTO order_products(order_id, product_id, quantity, actual_price) 
	VALUES (103, 4, 1, 2200.00);
INSERT INTO order_products(order_id, product_id, quantity, actual_price) 
	VALUES (103, 3, 1, 300.00);
INSERT INTO order_products(order_id, product_id, quantity, actual_price) 
	VALUES (103, 5, 1, 1000.00);
	

SELECT setval('orders_order_id_seq', 110, true);
SELECT setval('products_product_id_seq', 10, true);
