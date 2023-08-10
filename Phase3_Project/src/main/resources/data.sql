CREATE TABLE app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE purchase_report (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_date DATE NOT NULL,
    category VARCHAR(50) NOT NULL,
    product_id BIGINT,
    user_id BIGINT,
    amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (user_id) REFERENCES app_user(id)
);

INSERT INTO app_user (username, password, email)
VALUES
    ('john_doe', 'password123', 'john.doe@example.com'),
    ('jane_smith', 'pass456', 'jane.smith@example.com'),
     ('Sudha', '123', 'sudha@example.com');
    
INSERT INTO product (name, category, price)
VALUES
    ('Running Shoes', 'Footwear', 89.99),
    ('Basketball', 'Sports Equipment', 24.99);
    
INSERT INTO purchase_report (purchase_date, category, product_id, user_id, amount)
VALUES
    ('2023-08-01', 'Footwear', 1, 1, 89.99),
    ('2023-08-02', 'Sports Equipment', 2, 2, 24.99),
    ('2023-08-02', 'Footwear', 2, 2, 24.99)
    ;
