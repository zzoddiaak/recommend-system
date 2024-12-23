-- Таблица рейтингов (оценки пользователей для товаров)
CREATE TABLE ratings (
    rating_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id) ON DELETE CASCADE,
    product_id INT REFERENCES products(product_id) ON DELETE CASCADE,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    review TEXT,
    rated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);