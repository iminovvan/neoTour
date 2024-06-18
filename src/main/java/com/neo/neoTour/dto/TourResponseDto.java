package com.neo.neoTour.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

public record TourResponseDto (
        Long id,
        String name,
        String description,
        String place,
        String country,
        String image,
        List<ReviewResponseDto> reviews
        //List<BookingResponseDto> bookings
) implements Serializable {
}
