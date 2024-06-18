package com.neo.neoTour.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ReviewResponseDto(
        Long id,
        String username,
        String text,
        LocalDateTime reviewDate
) implements Serializable {
}
