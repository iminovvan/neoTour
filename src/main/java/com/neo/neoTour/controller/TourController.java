package com.neo.neoTour.controller;

import com.neo.neoTour.dto.TourListDto;
import com.neo.neoTour.dto.TourResponseDto;
import com.neo.neoTour.service.TourService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tours")
public class TourController {
    private final TourService tourService;

    @GetMapping("/{id}")
    public ResponseEntity<TourResponseDto> findTourById(@PathVariable Long id){
        return ResponseEntity.ok(tourService.findTourById(id));
    }

    @GetMapping("/featured")
    public ResponseEntity<List<TourListDto>> findFeaturedTours(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        return ResponseEntity.ok(tourService.findFeaturedTours(PageRequest.of(page, size)));
    }

    @GetMapping("/popular")
    public ResponseEntity<List<TourListDto>> findPopularTours(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        return ResponseEntity.ok(tourService.findPopularTours(PageRequest.of(page, size)));
    }

    @GetMapping("/most-visited")
    public ResponseEntity<List<TourListDto>> findMostVisitedTours(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        return ResponseEntity.ok(tourService.findMostVisitedTours(PageRequest.of(page, size)));
    }

    @GetMapping("/asian")
    public ResponseEntity<List<TourListDto>> findAsianTours(){
        return ResponseEntity.ok(tourService.findAsianTours());
    }

    @GetMapping("/europe")
    public ResponseEntity<List<TourListDto>> findEuropeanTours(){
        return ResponseEntity.ok(tourService.findEuropeanTours());
    }

    @GetMapping("recommended")
    public ResponseEntity<List<TourListDto>> findRecommendedTours(){
        return ResponseEntity.ok(tourService.findRecommendedTours());
    }

}
