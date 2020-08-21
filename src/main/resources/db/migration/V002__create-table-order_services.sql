CREATE TABLE IF NOT EXISTS order_services (
	id BIGINT NOT NULL AUTO_INCREMENT,
	client_id BIGINT NOT NULL,
	description VARCHAR(255) NOT NULL,
	price DECIMAL(10, 2) NOT NULL,
	status VARCHAR(20) NOT NULL,
	open_date DATETIME NOT NULL,
	finish_date DATETIME,
	
	PRIMARY KEY (id),
	FOREIGN KEY (client_id) REFERENCES clients (id)
);