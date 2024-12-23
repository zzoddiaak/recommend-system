package recommendsys.recommend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import recommendsys.recommend.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.stock > 0")
    List<Product> findAvailableProductsByCategory(String category);

    @Query("SELECT p FROM Product p JOIN Rating r ON p.productId = r.product.productId GROUP BY p ORDER BY AVG(r.rating) DESC")
    List<Product> findTopRatedProducts();
}
