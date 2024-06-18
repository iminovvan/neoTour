package com.neo.neoTour.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;

public record BookingRequestDto(
        Long tourId,
        @NotNull(message = "Phone number cannot be null.")
        @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Wrong phone number format.")
        String phoneNumber,
        @Min(value = 1, message = "Number of people must be greater than or equal to 1.")
        @Max(value = 10, message = "Number of people must be less than or equal to 10. ")
        int peopleAmount,
        @Size(max = 400, message = "Comment must be less than or equal to 400 characters.")
        String comment
) implements Serializable {
}
