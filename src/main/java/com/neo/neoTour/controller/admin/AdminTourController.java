package com.neo.neoTour.controller.admin;

import com.neo.neoTour.dto.TourRequestDto;
import com.neo.neoTour.dto.TourResponseDto;
import com.neo.neoTour.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/tours")
public class AdminTourController {
    private final TourService tourService;

    @GetMapping("/all")
    public ResponseEntity<List<TourResponseDto>> findAllTours(){
        return ResponseEntity.ok(tourService.findAllTours());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourResponseDto> getTourById(@PathVariable Long id){
        return ResponseEntity.ok(tourService.getTourById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTour(@RequestPart("dto") TourRequestDto tourRequestDto,
                                             @RequestPart("image") MultipartFile image){
        tourService.createTour(tourRequestDto, image);
        return ResponseEntity.ok("Tour created successfully.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TourResponseDto> updateTour(@RequestPart("dto") TourRequestDto tourRequestDto,
                                             @RequestPart("image") MultipartFile image,
                                             @PathVariable Long id){
        return ResponseEntity.ok(tourService.updateTour(tourRequestDto, image, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTour(@PathVariable Long id){
        tourService.deleteById(id);
        return ResponseEntity.ok("Tour deleted successfully.");
    }
}
