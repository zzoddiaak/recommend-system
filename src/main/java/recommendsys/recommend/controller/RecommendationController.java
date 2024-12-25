package recommendsys.recommend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import recommendsys.recommend.entity.Order;
import recommendsys.recommend.entity.Product;
import recommendsys.recommend.entity.Rating;
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

    // Получение данных о пользователе (имя, email, дата регистрации)
    @Operation(summary = "Получить информацию о пользователе",
            description = "Возвращает данные о пользователе, включая его имя, email и дату регистрации.")
    @GetMapping("/user/{userId}")
    public User getUserDetails(@PathVariable Long userId) {
        return recommendationService.getUserDetails(userId);
    }

    // Получение всех заказов пользователя
    @Operation(summary = "Получить заказы пользователя",
            description = "Возвращает список всех заказов пользователя.")
    @GetMapping("/user/{userId}/orders")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return recommendationService.getUserOrders(userId);
    }

    // Получение всех оценок пользователя
    @Operation(summary = "Получить оценки пользователя",
            description = "Возвращает список всех оценок товаров пользователем.")
    @GetMapping("/user/{userId}/ratings")
    public List<Rating> getUserRatings(@PathVariable Long userId) {
        return recommendationService.getUserRatings(userId);
    }

    @Operation(summary = "Получить топовые товары по рейтингу",
            description = "Возвращает список товаров с наивысшим рейтингом.")
    @GetMapping("/top-rated")
    public List<Product> getTopRatedProducts() {
        return recommendationService.recommendTopRatedProducts();
    }

    @Operation(summary = "Получить персонализированные рекомендации для пользователя",
            description = "Возвращает список товаров, рекомендованных для указанного пользователя на основе его предпочтений.")
    @GetMapping("/personal/{userId}")
    public List<Product> getPersonalRecommendations(@PathVariable Long userId) {
        return recommendationService.recommendProductsForUser(userId);
    }

    @Operation(summary = "Рекомендации на основе оценок пользователя",
            description = "Возвращает список товаров, которые рекомендованы пользователю на основе его оценок.")
    @GetMapping("/based-on-ratings/{userId}")
    public List<Product> getRecommendationsBasedOnRatings(@PathVariable Long userId) {
        return recommendationService.recommendBasedOnUserRatings(userId);
    }
}

