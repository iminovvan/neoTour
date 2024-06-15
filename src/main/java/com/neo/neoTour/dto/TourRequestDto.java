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

/*
* Create a NEW Tour:
* Must-have Data about it:
* Name
* Location: Place + Country + Continent
* Seasons
* Description
* Is it Featured? (for promotion)
* */