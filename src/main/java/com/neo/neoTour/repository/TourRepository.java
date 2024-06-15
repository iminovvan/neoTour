package com.neo.neoTour.repository;

import com.neo.neoTour.entity.Tour;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    @Query("SELECT t FROM Tour t ORDER BY t.createdDate DESC")
    Page<Tour> findFeaturedTours(Pageable pageable);
    @Query("SELECT t FROM Tour t WHERE t.viewCount != 0 ORDER BY t.viewCount DESC")
    Page<Tour> findPopularTours(Pageable pageable);

    @Query("SELECT t FROM Tour t WHERE t.bookingCount != 0 ORDER BY t.bookingCount DESC")
    Page<Tour> findMostVisitedTours(Pageable pageable);

    @Query("SELECT t FROM Tour t WHERE LOWER(t.location.continent)  = 'asia'")
    List<Tour> findAsianTours();

    @Query("SELECT t FROM Tour t WHERE LOWER(t.location.continent)  = 'europe'")
    List<Tour> findEuropeanTours();

    @Query("SELECT t FROM Tour t WHERE LOWER(t.recommendedSeasons) LIKE %:season%")
    //IN ('december', 'january', 'february')
    List<Tour> findToursBySeason(@Param("season") String season);

    /*

    @Query("SELECT t FROM Tour t WHERE LOWER(t.recommendedSeasons) LIKE %:winter% OR LOWER(t.recommendedSeasons) LIKE %:spring% OR LOWER(t.recommendedSeasons) LIKE %:summer% OR LOWER(t.recommendedSeasons) LIKE %:autumn%")
    List<Tour> findAllSeasonsTours(@Param("winter") String winter, @Param("spring") String spring, @Param("summer") String summer, @Param("autumn") String autumn);


     */
}
