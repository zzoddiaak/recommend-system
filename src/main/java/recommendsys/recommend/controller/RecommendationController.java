package recommendsys.recommend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import recommendsys.recommend.dto.OrderDTO;
import recommendsys.recommend.entity.Product;
import recommendsys.recommend.entity.User;
import recommendsys.recommend.service.RecommendationService;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/user/{userId}")
    public User getUserDetails(@PathVariable Long userId) {
        return recommendationService.getUserDetails(userId);
    }

    @GetMapping("/orders/{userId}")
    public List<OrderDTO> getUserOrders(@PathVariable Long userId) {
        return recommendationService.getUserOrders(userId);
    }

    @GetMapping("/top-rated")
    public List<Product> getTopRatedProducts() {
        return recommendationService.recommendTopRatedProducts();
    }

    @GetMapping("/personal/{userId}")
    public List<Product> getPersonalRecommendations(@PathVariable Long userId) {
        return recommendationService.recommendProductsForUser(userId);
    }

    @GetMapping("/based-on-ratings/{userId}")
    public List<Product> getRecommendationsBasedOnRatings(@PathVariable Long userId) {
        return recommendationService.recommendBasedOnUserRatings(userId);
    }
}
