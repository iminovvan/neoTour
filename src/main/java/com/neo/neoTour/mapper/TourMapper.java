package com.neo.neoTour.mapper;

import com.neo.neoTour.dto.TourListDto;
import com.neo.neoTour.dto.TourResponseDto;
import com.neo.neoTour.entity.Tour;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TourMapper {
    private final ReviewMapper reviewMapper;
    private final BookingMapper bookingMapper;

    public TourResponseDto convertToDto(Tour tour){
        return new TourResponseDto(
                tour.getId(),
                tour.getName(),
                tour.getDescription(),
                tour.getLocation().getPlace(),
                tour.getLocation().getCountry(),
                tour.getImage().getImageUrl(),
                        //getImages().stream().map(Image::getImageUrl).toList(), // create a string of image URLs
                tour.getReviews().stream().map(reviewMapper::convertToDto).toList()
                //tour.getBookings().stream().map(bookingMapper::convertToDto).toList()
        );
    }

    public List<TourResponseDto> convertToDtoList(List<Tour> tours){
        return tours.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TourListDto convertToSimpleDto(Tour tour){
        return new TourListDto(
                tour.getId(),
                tour.getImage().getImageUrl(),
                tour.getName()
        );
    }

    public List<TourListDto> convertToListOfDto(List<Tour> tours){
        return tours.stream()
                .map(this::convertToSimpleDto)
                .collect(Collectors.toList());
    }


}

