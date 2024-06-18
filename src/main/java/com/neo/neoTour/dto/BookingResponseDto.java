package com.neo.neoTour.dto;

import jakarta.validation.constraints.*;

public record BookingResponseDto(
        Long bookingId,
        String tourName,
        String phoneNumber,
        int peopleAmount,
        String comment
) {
}
