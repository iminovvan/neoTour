package com.neo.neoTour.controller.admin;

import com.neo.neoTour.dto.BookingRequestDto;
import com.neo.neoTour.dto.BookingResponseDto;
import com.neo.neoTour.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/bookings")
public class AdminBookingController {
    private final BookingService bookingService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTour(@PathVariable Long id){
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking successfully deleted.");
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookingResponseDto>> getAllBooking(){
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}
