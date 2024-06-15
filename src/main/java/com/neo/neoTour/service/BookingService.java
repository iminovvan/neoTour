package com.neo.neoTour.service;

import com.neo.neoTour.repository.BookingRepository;
import com.neo.neoTour.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final TourRepository tourRepository;
    private final BookingRepository bookingRepository;

    public void bookTour(){

    }
}
