package com.neo.neoTour.service;

import com.neo.neoTour.dto.ReviewRequestDto;
import com.neo.neoTour.dto.ReviewResponseDto;
import com.neo.neoTour.entity.Review;
import com.neo.neoTour.entity.Tour;
import com.neo.neoTour.entity.User;
import com.neo.neoTour.exception.NotFoundException;
import com.neo.neoTour.exception.TourNotFoundException;
import com.neo.neoTour.mapper.ReviewMapper;
import com.neo.neoTour.repository.ReviewRepository;
import com.neo.neoTour.repository.TourRepository;
import com.neo.neoTour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;

    public List<ReviewResponseDto> getReviewsByTour(Long tourId){
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new TourNotFoundException("Tour with id: " + tourId + " not found."));
        List<Review> reviews = reviewRepository.findReviewsByTour(tourId);
        return reviewMapper.convertToDtoList(reviews);
    }

    public List<ReviewResponseDto> getAllReviews(){
        List<Review> allReviews = reviewRepository.findAll();
        return reviewMapper.convertToDtoList(allReviews);
    }

    public void deleteReview(Long id){
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Review with id: " + id + " not found."));
        reviewRepository.deleteById(id);
    }

    public ResponseEntity<String> addReview(ReviewRequestDto reviewRequestDto){
        if(reviewRequestDto.tourId() == null || reviewRequestDto.username() == null
        || reviewRequestDto.text() == null){
            return ResponseEntity.ok("Username and comment must be filled.");
        }
        Review review = new Review();
        review.setUsername(reviewRequestDto.username());
        review.setReviewText(reviewRequestDto.text());
        review.setReviewDate(LocalDateTime.now());
        Tour tour = tourRepository.findById(reviewRequestDto.tourId())
                .orElseThrow(() -> new TourNotFoundException("Tour with id: " + reviewRequestDto.tourId() +" not found."));
        User user = userRepository.findById(reviewRequestDto.userId())
                .orElseThrow(() -> new NotFoundException("User with id: " + reviewRequestDto.userId() + " not found."));
        review.setTour(tour);
        review.setUser(user);

        user.addReview(review);

        reviewRepository.save(review);
        return ResponseEntity.ok("Review created successfully");
    }
}
