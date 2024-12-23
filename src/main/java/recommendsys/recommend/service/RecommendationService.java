package recommendsys.recommend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recommendsys.recommend.entity.Product;
import recommendsys.recommend.entity.Rating;
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

    /**
     * Рекомендации на основе рейтингов товаров
     * @return Список топ-товаров
     */
    public List<Product> recommendTopRatedProducts() {
        return productRepository.findTopRatedProducts()
                .stream()
                .limit(10) // Берем топ-10 по рейтингу
                .collect(Collectors.toList());
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
