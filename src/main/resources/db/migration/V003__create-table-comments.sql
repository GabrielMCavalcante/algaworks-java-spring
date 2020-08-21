CREATE TABLE IF NOT EXISTS comments (
	id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    send_date DATETIME NOT NULL,
    
    order_service_id BIGINT NOT NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (order_service_id) REFERENCES order_services (id)
);