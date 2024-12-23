-- Наполнение таблицы users
INSERT INTO users (username, email) VALUES
('user1', 'user1@example.com'),
('user2', 'user2@example.com'),
('user3', 'user3@example.com');

-- Наполнение таблицы products
INSERT INTO products (name, category, price, stock) VALUES
('Smartphone X', 'Electronics', 699.99, 50),
('Laptop Pro', 'Electronics', 1299.99, 30),
('Wireless Earbuds', 'Accessories', 199.99, 100),
('Coffee Maker', 'Home Appliances', 99.99, 20),
('Desk Chair', 'Furniture', 149.99, 15);

-- Наполнение таблицы orders
INSERT INTO orders (user_id) VALUES
(1), (2), (3);

-- Наполнение таблицы order_items
INSERT INTO order_items (order_id, product_id, quantity, total_price) VALUES
(1, 1, 1, 699.99),
(1, 3, 2, 399.98),
(2, 2, 1, 1299.99),
(3, 4, 1, 99.99);

-- Наполнение таблицы ratings
INSERT INTO ratings (user_id, product_id, rating, review) VALUES
(1, 1, 5, 'Excellent smartphone!'),
(2, 2, 4, 'Great laptop, but a bit pricey.'),
(3, 4, 5, 'Makes perfect coffee every time.');
