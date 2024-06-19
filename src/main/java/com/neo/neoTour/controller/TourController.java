package com.neo.neoTour.controller;

import com.neo.neoTour.dto.TourListDto;
import com.neo.neoTour.dto.TourResponseDto;
import com.neo.neoTour.service.TourService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Tours",
        description = "Public endpoints for users to get list of tours by category"
)
@RestController
@AllArgsConstructor
@RequestMapping("/tours")
public class TourController {
    private final TourService tourService;

    @Operation(
            summary = "Get tour by id",
            description = "Every user can get tour by id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Tour not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TourResponseDto> findTourById(@PathVariable Long id){
        return ResponseEntity.ok(tourService.findTourById(id));
    }

    @Operation(
            summary = "Get featured tours",
            description = "Every user can get a list of tours in 'featured' category"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping("/featured")
    public ResponseEntity<List<TourListDto>> findFeaturedTours(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        return ResponseEntity.ok(tourService.findFeaturedTours(PageRequest.of(page, size)));
    }

    @Operation(
            summary = "Get popular tours",
            description = "Every user can get a list of tours in 'popular' category"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping("/popular")
    public ResponseEntity<List<TourListDto>> findPopularTours(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        return ResponseEntity.ok(tourService.findPopularTours(PageRequest.of(page, size)));
    }

    @Operation(
            summary = "Get most visited tours",
            description = "Every user can get a list of tours in 'most visited' category"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping("/most-visited")
    public ResponseEntity<List<TourListDto>> findMostVisitedTours(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        return ResponseEntity.ok(tourService.findMostVisitedTours(PageRequest.of(page, size)));
    }

    @Operation(
            summary = "Get asian tours",
            description = "Every user can get a list of tours in 'asia' category"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping("/asia")
    public ResponseEntity<List<TourListDto>> findAsianTours(){
        return ResponseEntity.ok(tourService.findAsianTours());
    }

    @Operation(
            summary = "Get european tours",
            description = "Every user can get a list of tours in 'europe' category"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping("/europe")
    public ResponseEntity<List<TourListDto>> findEuropeanTours(){
        return ResponseEntity.ok(tourService.findEuropeanTours());
    }

    @Operation(
            summary = "Get recommended tours",
            description = "Every user can get a list of tours in 'recommended' category"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping("recommended")
    public ResponseEntity<List<TourListDto>> findRecommendedTours(){
        return ResponseEntity.ok(tourService.findRecommendedTours());
    }

}
