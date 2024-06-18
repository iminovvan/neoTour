package com.neo.neoTour.dto;

import com.neo.neoTour.entity.Role;

import java.util.List;

public record UserResponseDto(
        Long userId,
        String phoneNumber,
        String roles,
        List<ReviewResponseDto> reviews,
        List<BookingResponseDto> bookings
) {
}
