-- To wykonać tylko raz, a potem połączyć się z tą bazą...
-- CREATE DATABASE sklep OWNER kurs ENCODING 'utf-8';

-- Reszta instrukcji usuwa i od nowa tworzy tabele.
-- W tej wersji preferujemy zwięzłe zapisy.

DROP TABLE IF EXISTS order_products;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS products;

CREATE TABLE products (
	product_id SERIAL PRIMARY KEY,
	product_name VARCHAR(100) NOT NULL UNIQUE,
	price NUMERIC(10,2) NOT NULL,
	description TEXT
);

CREATE TABLE customers (
	email VARCHAR(100) PRIMARY KEY,
	customer_name VARCHAR(100) NOT NULL,
	phone VARCHAR(20) NOT NULL,
	address VARCHAR(100),
	postal_code VARCHAR(6),
	city VARCHAR(100)
);

CREATE TABLE orders (
	order_id SERIAL PRIMARY KEY,
	customer_email VARCHAR(100) NOT NULL REFERENCES customers(email),
	order_date TIMESTAMP NOT NULL,
	order_status VARCHAR(10) NOT NULL CHECK (order_status IN ('unpaid', 'paid', 'shipped', 'closed'))
);

CREATE TABLE order_products (
	order_id INTEGER NOT NULL REFERENCES orders(order_id),
	product_id INTEGER NOT NULL REFERENCES products(product_id),
	quantity INTEGER NOT NULL DEFAULT 1,
	actual_price NUMERIC(10,2) NOT NULL,
	PRIMARY KEY(order_id, product_id)
);
