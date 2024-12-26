package recommendsys.recommend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recommendsys.recommend.dto.OrderDTO;
import recommendsys.recommend.dto.OrderItemDTO;
import recommendsys.recommend.entity.Product;
import recommendsys.recommend.entity.Rating;
import recommendsys.recommend.entity.Order;
import recommendsys.recommend.entity.User;
import recommendsys.recommend.repository.*;

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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserDetails(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<OrderDTO> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findOrdersByUserId(userId);

        return orders.stream()
                .map(order -> {
                    User user = order.getUser();

                    List<OrderItemDTO> orderItems = order.getOrderItems().stream()
                            .map(item -> new OrderItemDTO(item.getProduct().getName(), item.getQuantity(), item.getTotalPrice()))
                            .collect(Collectors.toList());

                    Double totalPrice = orderItems.stream()
                            .mapToDouble(OrderItemDTO::getTotalPrice)
                            .sum();

                    return new OrderDTO(order.getOrderId(), order.getOrderDate(), user.getUsername(), orderItems, totalPrice);
                })
                .collect(Collectors.toList());
    }


    public List<Product> recommendTopRatedProducts() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            double averageRating = ratingRepository.calculateAverageRating(product.getProductId());
            product.setRating(averageRating);  // Set rating for each product
        }
        return products;
    }

    public List<Product> recommendProductsForUser(Long userId) {
        List<Product> mostPurchasedProducts = orderItemRepository.findMostPurchasedProductsByUser(userId);
        Set<String> userPreferredCategories = mostPurchasedProducts.stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());

        return userPreferredCategories.stream()
                .flatMap(category -> productRepository.findAvailableProductsByCategory(category).stream())
                .distinct()
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Product> recommendBasedOnUserRatings(Long userId) {
        List<Rating> userRatings = ratingRepository.findRatingsByUserId(userId);
        Set<String> highlyRatedCategories = userRatings.stream()
                .filter(rating -> rating.getRating() >= 4)
                .map(rating -> rating.getProduct().getCategory())
                .collect(Collectors.toSet());

        return highlyRatedCategories.stream()
                .flatMap(category -> productRepository.findAvailableProductsByCategory(category).stream())
                .distinct()
                .limit(10)
                .collect(Collectors.toList());
    }
}
