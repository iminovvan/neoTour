package com.neo.neoTour.repository;

import com.neo.neoTour.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT a FROM Review a WHERE a.tour.id = :tourId ")
    List<Review> findReviewsByTour(@Param("tourId") Long tourId);
}
