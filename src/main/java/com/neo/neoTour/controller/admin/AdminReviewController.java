package com.neo.neoTour.controller.admin;

import com.neo.neoTour.dto.ReviewRequestDto;
import com.neo.neoTour.dto.ReviewResponseDto;
import com.neo.neoTour.mapper.ReviewMapper;
import com.neo.neoTour.service.ReviewService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Admin Review Controller",
        description = "Private endpoints for admins to control reviews"
)
@RestController
@RequiredArgsConstructor
@Hidden
@RequestMapping("/admin/reviews")
public class AdminReviewController {
    private final ReviewService reviewService;

    @Operation(
            summary = "Get all reviews",
            description = "Admin user can get all reviews for all tours"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "You don't have permission to access this resource")
    })
    @GetMapping("/all")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews(){
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @Operation(
            summary = "Delete review",
            description = "Admin user can delete review by id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "You don't have permission to access this resource"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review successfully deleted.");
    }

    @Operation(
            summary = "Create review for a tour",
            description = "Admin user can add review for a specific tour"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Review created successfully"),
            @ApiResponse(responseCode = "403", description = "You don't have permission to access this resource"),
            @ApiResponse(responseCode = "404", description = "Tour not found")
    })
    @PostMapping("/create")
    public ResponseEntity<String> addReview(@RequestBody ReviewRequestDto reviewRequestDto){
        reviewService.addReview(reviewRequestDto);
        return ResponseEntity.ok("Review created successfully!");
    }
}
