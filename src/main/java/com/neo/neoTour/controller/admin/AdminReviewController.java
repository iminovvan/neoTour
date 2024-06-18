package com.neo.neoTour.controller.admin;

import com.neo.neoTour.dto.ReviewRequestDto;
import com.neo.neoTour.dto.ReviewResponseDto;
import com.neo.neoTour.mapper.ReviewMapper;
import com.neo.neoTour.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/reviews")
public class AdminReviewController {
    private final ReviewService reviewService;

    @GetMapping("/all")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews(){
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review successfully deleted.");
    }

    @PostMapping("/create")
    public ResponseEntity<String> addReview(@RequestBody ReviewRequestDto reviewRequestDto){
        reviewService.addReview(reviewRequestDto);
        return ResponseEntity.ok("Review created successfully!");
    }
}
