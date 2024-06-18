package com.neo.neoTour.dto;
// Request to Create

public record TourRequestDto(
        String name,
        String place,
        String country,
        String continent,
        String recommendedSeasons,
        String description
) {}
