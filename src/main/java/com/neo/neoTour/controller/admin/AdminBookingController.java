package com.neo.neoTour.controller.admin;

import com.neo.neoTour.dto.BookingRequestDto;
import com.neo.neoTour.dto.BookingResponseDto;
import com.neo.neoTour.service.BookingService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Admin Booking Controller",
        description = "Private endpoints for admins to control bookings"
)
@RestController
@RequiredArgsConstructor
@Hidden
@RequestMapping("/admin/bookings")
public class AdminBookingController {
    private final BookingService bookingService;

    @Operation(
            summary = "Delete booking",
            description = "Admin can delete booking by id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Tour not found"),
            @ApiResponse(responseCode = "403", description = "You don't have permission to access this resource")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTour(@PathVariable Long id){
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking successfully deleted.");
    }

    @Operation(
            summary = "Get all bookings",
            description = "Get list of all bookings for all tours"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "403", description = "You don't have permission to access this resource")
    })
    @GetMapping("/all")
    public ResponseEntity<List<BookingResponseDto>> getAllBooking(){
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}
