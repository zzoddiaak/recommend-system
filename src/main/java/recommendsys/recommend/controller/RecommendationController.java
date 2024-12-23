package recommendsys.recommend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import recommendsys.recommend.entity.Product;
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

    @Operation(summary = "Получить популярные товары по категории",
            description = "Возвращает список товаров, которые наиболее популярны в указанной категории.")
    @GetMapping("/popular")
    public List<Product> getPopularProductsByCategory(
            @Parameter(description = "Категория товаров для рекомендации") @RequestParam String category) {
        return recommendationService.recommendPopularProductsByCategory(category);
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
    public List<Product> getPersonalRecommendations(
            @Parameter(description = "ID пользователя для получения персональных рекомендаций") @PathVariable Long userId) {
        return recommendationService.recommendProductsForUser(userId);
    }

    @Operation(summary = "Рекомендации на основе оценок пользователя",
            description = "Возвращает список товаров, которые рекомендованы пользователю на основе его оценок.")
    @GetMapping("/based-on-ratings/{userId}")
    public List<Product> getRecommendationsBasedOnRatings(
            @Parameter(description = "ID пользователя для получения рекомендаций на основе оценок") @PathVariable Long userId) {
        return recommendationService.recommendBasedOnUserRatings(userId);
    }
}
