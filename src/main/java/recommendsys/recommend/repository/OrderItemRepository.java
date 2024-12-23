package recommendsys.recommend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import recommendsys.recommend.entity.OrderItem;
import recommendsys.recommend.entity.Product;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT oi.product FROM OrderItem oi WHERE oi.order.user.userId = :userId GROUP BY oi.product ORDER BY SUM(oi.quantity) DESC")
    List<Product> findMostPurchasedProductsByUser(Long userId);
}
