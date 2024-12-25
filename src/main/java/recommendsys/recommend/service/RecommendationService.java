package recommendsys.recommend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recommendsys.recommend.entity.Order;
import recommendsys.recommend.entity.Product;
import recommendsys.recommend.entity.Rating;
import recommendsys.recommend.entity.User;
import recommendsys.recommend.repository.OrderItemRepository;
import recommendsys.recommend.repository.OrderRepository;
import recommendsys.recommend.repository.ProductRepository;
import recommendsys.recommend.repository.RatingRepository;
import recommendsys.recommend.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;
@Service
@Transactional
public class RecommendationService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public RecommendationService(UserRepository userRepository,
                                 ProductRepository productRepository,
                                 OrderRepository orderRepository,
                                 OrderItemRepository orderItemRepository,
                                 RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.ratingRepository = ratingRepository;
    }

    // Метод для получения данных о пользователе, включая его покупки и рейтинги
    public User getUserDetails(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // Метод для получения всех заказов пользователя
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    // Метод для получения всех оценок пользователя
    public List<Rating> getUserRatings(Long userId) {
        return ratingRepository.findRatingsByUserId(userId);
    }

    /**
     * Рекомендации на основе рейтингов товаров
     * @return Список топ-товаров
     */
    public List<Product> recommendTopRatedProducts() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            double averageRating = ratingRepository.calculateAverageRating(product.getProductId());
            product.setRating(averageRating);  // Сеттинг рейтинга
        }
        return products;
    }

    /**
     * Персонализированные рекомендации на основе истории покупок пользователя
     * @param userId ID пользователя
     * @return Список персональных рекомендаций
     */
    public List<Product> recommendProductsForUser(Long userId) {
        List<Product> mostPurchasedProducts = orderItemRepository.findMostPurchasedProductsByUser(userId);

        // Рекомендации: популярные товары из той же категории, что и часто покупаемые
        Set<String> userPreferredCategories = mostPurchasedProducts.stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());

        return userPreferredCategories.stream()
                .flatMap(category -> productRepository.findAvailableProductsByCategory(category).stream())
                .distinct()
                .limit(10) // Берем топ-10
                .collect(Collectors.toList());
    }

    /**
     * Рекомендации на основе товаров, которые похожи на недавно оцененные пользователем
     * @param userId ID пользователя
     * @return Список рекомендованных товаров
     */
    public List<Product> recommendBasedOnUserRatings(Long userId) {
        List<Rating> userRatings = ratingRepository.findRatingsByUserId(userId);

        Set<String> highlyRatedCategories = userRatings.stream()
                .filter(rating -> rating.getRating() >= 4)
                .map(rating -> rating.getProduct().getCategory())
                .collect(Collectors.toSet());

        return highlyRatedCategories.stream()
                .flatMap(category -> productRepository.findAvailableProductsByCategory(category).stream())
                .distinct()
                .limit(10) // Берем топ-10 товаров
                .collect(Collectors.toList());
    }
}
