package com.neo.neoTour.controller;

import com.neo.neoTour.dto.ReviewResponseDto;
import com.neo.neoTour.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Review",
        description = "Public endpoint for users to find reviews for each tour."
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(
            summary = "Get all reviews for a specific tour",
            description = "Every user can get a list of reviews by tour id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Tour not found")
    })
    @GetMapping("/{tourId}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByTour(@PathVariable Long tourId){
        return ResponseEntity.ok(reviewService.getReviewsByTour(tourId));
    }

}
