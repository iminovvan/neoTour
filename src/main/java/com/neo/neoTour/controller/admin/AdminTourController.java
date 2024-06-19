package com.neo.neoTour.controller.admin;

import com.neo.neoTour.dto.TourRequestDto;
import com.neo.neoTour.dto.TourResponseDto;
import com.neo.neoTour.service.TourService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(
        name = "Admin Tours Controller",
        description = "Private endpoints for admins to control tours"
)
@RestController
@RequiredArgsConstructor
@Hidden
@RequestMapping("/admin/tours")
public class AdminTourController {
    private final TourService tourService;

    @Operation(
            summary = "Get all tours",
            description = "Admin user can get all tours"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "You don't have permission to access this resource")
    })
    @GetMapping("/all")
    public ResponseEntity<List<TourResponseDto>> findAllTours(){
        return ResponseEntity.ok(tourService.findAllTours());
    }

    @Operation(
            summary = "Get tour by id",
            description = "Admin user can get tour by id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "You don't have permission to access this resource"),
            @ApiResponse(responseCode = "404", description = "Tour not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TourResponseDto> getTourById(@PathVariable Long id){
        return ResponseEntity.ok(tourService.getTourById(id));
    }

    @Operation(
            summary = "Create a tour",
            description = "Admin user can create a new tour"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tour created successfully"),
            @ApiResponse(responseCode = "403", description = "You don't have permission to access this resource")
    })
    @PostMapping("/create")
    public ResponseEntity<String> createTour(@RequestPart("dto") TourRequestDto tourRequestDto,
                                             @RequestPart("image") MultipartFile image){
        tourService.createTour(tourRequestDto, image);
        return ResponseEntity.ok("Tour created successfully");
    }

    @Operation(
            summary = "Update tour",
            description = "Admin user can update existing tour"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "You don't have permission to access this resource"),
            @ApiResponse(responseCode = "404", description = "Tour not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<TourResponseDto> updateTour(@RequestPart("dto") TourRequestDto tourRequestDto,
                                             @RequestPart("image") MultipartFile image,
                                             @PathVariable Long id){
        return ResponseEntity.ok(tourService.updateTour(tourRequestDto, image, id));
    }

    @Operation(
            summary = "Delete tour",
            description = "Admin user can delete tour by id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tour deleted successfully"),
            @ApiResponse(responseCode = "403", description = "You don't have permission to access this resource"),
            @ApiResponse(responseCode = "404", description = "Tour not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTour(@PathVariable Long id){
        tourService.deleteById(id);
        return ResponseEntity.ok("Tour deleted successfully");
    }
}
