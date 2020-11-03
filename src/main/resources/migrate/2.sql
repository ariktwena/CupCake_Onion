CREATE TABLE IF NOT EXISTS item (
item_id INT NOT NULL AUTO_INCREMENT,
item_name VARCHAR(45) NOT NULL,
item_des VARCHAR(255) NOT NULL,
item_price DECIMAL(8,2) NOT NULL DEFAULT 0,
item_image VARCHAR(45) NOT NULL,
PRIMARY KEY (item_id),
UNIQUE KEY item_name_UNIQUE (item_name ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS topping (
topping_id INT NOT NULL AUTO_INCREMENT,
topping_name VARCHAR(45) NOT NULL,
topping_price DECIMAL(8,2) NOT NULL DEFAULT 0,
PRIMARY KEY (topping_id),
UNIQUE INDEX topping_name_UNIQUE (topping_name ASC) VISIBLE);

INSERT INTO topping (topping_name, topping_price) VALUES
('Standard', 0),
('Chocolate', 5),
('Blueberry', 5),
('Raspberry', 5),
('Crispy', 6),
('Strawberry', 6),
('Rum/Raisin', 7),
('Orange', 8),
('Lemon', 8),
('Blue cheese', 9);

CREATE TABLE IF NOT EXISTS bottom (
bottom_id INT NOT NULL AUTO_INCREMENT,
bottom_name VARCHAR(45) NOT NULL,
bottom_price DECIMAL(8,2) NOT NULL DEFAULT 0,
PRIMARY KEY (bottom_id),
UNIQUE INDEX bottom_name_UNIQUE (bottom_name ASC) VISIBLE);

INSERT INTO bottom (bottom_name, bottom_price) VALUES
('Standard', 0),
('Chocolate', 5),
('Vanilla', 5),
('Nutmeg', 5),
('Pistacio', 6),
('Almond', 7);

CREATE TABLE IF NOT EXISTS shipping (
ship_id INT NOT NULL AUTO_INCREMENT,
ship_adr VARCHAR(150) NOT NULL,
ship_zip INT NOT NULL,
ship_city VARCHAR(45) NOT NULL,
fk_user_id int NOT NULL,
PRIMARY KEY (ship_id),
FOREIGN KEY (fk_user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS orders (
order_id INT NOT NULL AUTO_INCREMENT,
order_pay VARCHAR(45) NOT NULL,
order_info VARCHAR(255) NULL,
order_time VARCHAR(45) NOT NULL,
order_total DECIMAL(8,2) NOT NULL DEFAULT 0,
order_ship VARCHAR(45) NOT NULL,
order_status VARCHAR(45) NOT NULL DEFAULT 'pending',
order_active TINYINT NOT NULL DEFAULT 1,
fk_user_id int NOT NULL,
fk_ship_id int NOT NULL,
PRIMARY KEY (order_id),
FOREIGN KEY (fk_user_id) REFERENCES users(user_id),
FOREIGN KEY (fk_ship_id) REFERENCES shipping(ship_id)
);

CREATE TABLE IF NOT EXISTS order_details (
detail_id INT NOT NULL AUTO_INCREMENT,
fk_item_id int NOT NULL,
fk_topping_id int NOT NULL,
fk_bottom_id int NOT NULL,
detail_qty INT NOT NULL DEFAULT 0,
detail_price DECIMAL(8,2) NOT NULL DEFAULT 0,
detail_subtotal DECIMAL(8,2) NOT NULL,
fk_order_id int NOT NULL,
PRIMARY KEY (detail_id),
FOREIGN KEY (fk_item_id) REFERENCES item(item_id),
FOREIGN KEY (fk_topping_id) REFERENCES topping(topping_id),
FOREIGN KEY (fk_bottom_id) REFERENCES bottom(bottom_id),
FOREIGN KEY (fk_order_id) REFERENCES orders(order_id)
);


-- Husk at update jeres database version.
UPDATE properties
SET value = '2'
WHERE name = 'version';
