package com.neo.neoTour.controller;

import com.neo.neoTour.dto.BookingRequestDto;
import com.neo.neoTour.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Bookings", description = "Public endpoints for tour booking")
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    @Operation(
            summary = "Book a tour",
            description = "Every user can create a new booking"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tour booked successfully."),
            @ApiResponse(responseCode = "401", description = "Invalid data provided."),
            @ApiResponse(responseCode = "500", description = "Tour cannot be booked.")
    })
    @PostMapping("/book")
    public ResponseEntity<String> bookTour(@RequestBody @Valid BookingRequestDto bookingRequestDto){
        try{
            bookingService.bookTour(bookingRequestDto);
            return ResponseEntity.ok("Your tour has been booked!");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your tour cannot be booked.");
        }
    }
}
