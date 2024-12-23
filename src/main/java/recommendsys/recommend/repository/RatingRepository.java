package recommendsys.recommend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import recommendsys.recommend.entity.Rating;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("SELECT r FROM Rating r WHERE r.user.userId = :userId")
    List<Rating> findRatingsByUserId(Long userId);

    @Query("SELECT r FROM Rating r WHERE r.product.productId = :productId")
    List<Rating> findRatingsByProductId(Long productId);
}
