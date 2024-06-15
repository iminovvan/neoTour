package com.neo.neoTour.dto;

import java.io.Serializable;

public record ReviewRequestDto(
        Long id,
        String username,
        String text
) implements Serializable {
}
