-- To wykonać tylko raz, a potem połączyć się z tą bazą...
-- CREATE DATABASE sklep OWNER kurs ENCODING 'utf-8';

-- Reszta instrukcji usuwa i od nowa tworzy tabele.
-- W tej wersji FEREIGN KEY itp zapisane w bardziej rozbudowany sposób.
-- Zamiast SERIAL, które jest tylko skróconym zapisem na to samo, tutaj jawnie tworzymy sekwencje i podpinamy jako DEFAULT do tabel.
-- Używamy też typu enum (możliwość PostgreSQL, poza standardem SQL)

DROP TABLE IF EXISTS photos;
DROP TABLE IF EXISTS order_products;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS products CASCADE;
DROP TYPE IF EXISTS order_status;

CREATE SEQUENCE products_product_id_seq
	AS INTEGER START WITH 1;
	
CREATE TABLE products (
	product_id INTEGER DEFAULT nextval('products_product_id_seq'),
	product_name VARCHAR(100) NOT NULL UNIQUE,
	price NUMERIC(10,2) NOT NULL CHECK (price > 0),
	description TEXT,
	PRIMARY KEY (product_id)
);

ALTER SEQUENCE products_product_id_seq OWNED BY products.product_id;


CREATE TABLE customers (
	email VARCHAR(100),
	customer_name VARCHAR(100) NOT NULL,
	phone VARCHAR(20) NOT NULL,
	address VARCHAR(100),
	postal_code VARCHAR(6),
	city VARCHAR(100),
	PRIMARY KEY (email)
);

CREATE TYPE order_status AS ENUM (
	'unpaid',
	'paid',
	'shipped',
	'closed'
);

CREATE SEQUENCE orders_order_id_seq
	AS INTEGER START WITH 101;

CREATE TABLE orders (
	order_id INTEGER DEFAULT nextval('orders_order_id_seq'),
	customer_email VARCHAR(100) NOT NULL,
	order_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
	order_status order_status NOT NULL DEFAULT 'unpaid',
		
	PRIMARY KEY (order_id),
	FOREIGN KEY (customer_email) REFERENCES customers(email)
);

ALTER SEQUENCE orders_order_id_seq OWNED BY orders.order_id;

CREATE TABLE order_products (
	order_id INTEGER NOT NULL,
	product_id INTEGER NOT NULL,
	quantity INTEGER NOT NULL CHECK (quantity > 0) DEFAULT 1,
	actual_price NUMERIC(10,2) NOT NULL,
	actual_vat NUMERIC(2,2) NOT NULL,  
	
	PRIMARY KEY (order_id, product_id),
	FOREIGN KEY (order_id) REFERENCES orders(order_id),
	FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- Tak wyglądałaby definicja tabeli, w której przechowywane są dane binarne, np. zdjęcia.
CREATE TABLE photos(
	product_id INTEGER NOT NULL,
	nr SMALLINT NOT NULL CHECK (nr > 0),
	bytes BYTEA,
	PRIMARY KEY (product_id, nr),
	FOREIGN KEY (product_id) REFERENCES products(product_id)
);
