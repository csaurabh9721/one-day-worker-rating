package com.rating_service.Repository;

import com.rating_service.Entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByReceiverId(Long receiverId);

    List<Rating> findByGiverId(Long giverId);

    @Query("SELECT r FROM Rating r WHERE r.receiverId = :receiverId AND r.score >= :minRating")
    List<Rating> findRatingsForReceiverWithMinValue(Long receiverId, int minRating);

    @Query(
            value = "SELECT * FROM ratings r WHERE r.receiver_id = :receiverId ORDER BY r.created_at DESC LIMIT 5",
            nativeQuery = true
    )
    List<Rating> findLatestRatingsForReceiver(Long receiverId);
}
