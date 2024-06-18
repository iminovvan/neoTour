package com.neo.neoTour.mapper;

import com.neo.neoTour.dto.BookingResponseDto;
import com.neo.neoTour.entity.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    public BookingResponseDto convertToDto(Booking booking){
        return new BookingResponseDto(
                booking.getId(),
                booking.getTour().getName(),
                booking.getPhoneNumber(),
                booking.getPeopleAmount(),
                booking.getComment()
        );
    }

    public List<BookingResponseDto> convertToDtoList(List<Booking> bookings){
        return bookings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
