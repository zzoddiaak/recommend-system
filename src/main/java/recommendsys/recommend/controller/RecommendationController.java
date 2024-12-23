package recommendsys.recommend.controller;

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

    /**
     * Рекомендации популярных товаров из категории
     * @param category Категория товаров
     * @return Список рекомендованных товаров
     */
    @GetMapping("/popular")
    public List<Product> getPopularProductsByCategory(@RequestParam String category) {
        return recommendationService.recommendPopularProductsByCategory(category);
    }

    /**
     * Рекомендации топовых товаров по рейтингу
     * @return Список топовых товаров
     */
    @GetMapping("/top-rated")
    public List<Product> getTopRatedProducts() {
        return recommendationService.recommendTopRatedProducts();
    }

    /**
     * Персонализированные рекомендации для пользователя
     * @param userId ID пользователя
     * @return Список персональных рекомендаций
     */
    @GetMapping("/personal/{userId}")
    public List<Product> getPersonalRecommendations(@PathVariable Long userId) {
        return recommendationService.recommendProductsForUser(userId);
    }

    /**
     * Рекомендации на основе оценок пользователя
     * @param userId ID пользователя
     * @return Список рекомендованных товаров
     */
    @GetMapping("/based-on-ratings/{userId}")
    public List<Product> getRecommendationsBasedOnRatings(@PathVariable Long userId) {
        return recommendationService.recommendBasedOnUserRatings(userId);
    }
}
