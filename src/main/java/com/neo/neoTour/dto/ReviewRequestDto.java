package com.neo.neoTour.dto;

import java.io.Serializable;

public record ReviewRequestDto(
        Long tourId,
        Long userId,
        String username,
        String text
) implements Serializable {
}
