-- Наполнение таблицы users
INSERT INTO users (username, email) VALUES
('user1', 'user1@example.com'),
('user2', 'user2@example.com'),
('user3', 'user3@example.com'),
('user4', 'user4@example.com'),
('user5', 'user5@example.com'),
('user6', 'user6@example.com'),
('user7', 'user7@example.com'),
('user8', 'user8@example.com'),
('user9', 'user9@example.com'),
('user10', 'user10@example.com');

-- Наполнение таблицы products
INSERT INTO products (name, category, price, stock) VALUES
('Smartphone X', 'Electronics', 699.99, 50),
('Laptop Pro', 'Electronics', 1299.99, 30),
('Wireless Earbuds', 'Accessories', 199.99, 100),
('Coffee Maker', 'Home Appliances', 99.99, 20),
('Desk Chair', 'Furniture', 149.99, 15),
('Gaming Console', 'Electronics', 499.99, 25),
('Blender Pro', 'Home Appliances', 89.99, 40),
('Office Desk', 'Furniture', 249.99, 10),
('Smart Watch', 'Electronics', 299.99, 60),
('Vacuum Cleaner', 'Home Appliances', 149.99, 30);

-- Наполнение таблицы orders
INSERT INTO orders (user_id) VALUES
(1), (2), (3), (4), (5), (6), (7), (8), (9), (10);

-- Наполнение таблицы order_items
INSERT INTO order_items (order_id, product_id, quantity, total_price) VALUES
(1, 1, 1, 699.99),
(1, 3, 2, 399.98),
(2, 2, 1, 1299.99),
(3, 4, 1, 99.99),
(3, 5, 1, 149.99),
(4, 6, 1, 499.99),
(5, 7, 1, 89.99),
(5, 8, 1, 249.99),
(6, 9, 1, 299.99),
(7, 10, 2, 299.98),
(8, 1, 1, 699.99),
(9, 2, 1, 1299.99),
(10, 3, 3, 599.97);

-- Наполнение таблицы ratings
INSERT INTO ratings (user_id, product_id, rating, review) VALUES
(1, 1, 5, 'Excellent smartphone!'),
(2, 2, 4, 'Great laptop, but a bit pricey.'),
(3, 4, 5, 'Makes perfect coffee every time.'),
(4, 6, 5, 'Awesome gaming console!'),
(5, 7, 4, 'Decent blender for the price.'),
(6, 8, 3, 'Good desk, but not sturdy enough.'),
(7, 9, 4, 'Stylish and functional smart watch.'),
(8, 10, 5, 'Highly efficient vacuum cleaner.'),
(9, 5, 4, 'Comfortable chair for work.'),
(10, 3, 5, 'Fantastic sound quality!');
