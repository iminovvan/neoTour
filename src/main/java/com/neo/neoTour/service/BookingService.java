package com.neo.neoTour.service;

import com.neo.neoTour.dto.BookingRequestDto;
import com.neo.neoTour.dto.BookingResponseDto;
import com.neo.neoTour.entity.Booking;
import com.neo.neoTour.entity.Tour;
import com.neo.neoTour.entity.User;
import com.neo.neoTour.exception.EmptyListException;
import com.neo.neoTour.exception.NotFoundException;
import com.neo.neoTour.exception.TourNotFoundException;
import com.neo.neoTour.mapper.BookingMapper;
import com.neo.neoTour.repository.BookingRepository;
import com.neo.neoTour.repository.TourRepository;
import com.neo.neoTour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final TourRepository tourRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;
    private final UserService userService;

    public void bookTour(BookingRequestDto bookingRequestDto){
        Tour tour = tourRepository.findById(bookingRequestDto.tourId())
                .orElseThrow(() -> new TourNotFoundException("Tour with id: " + bookingRequestDto.tourId() + " not found."));

        validateBooking(bookingRequestDto);


        User user;
        if (userRepository.existsByPhoneNumber(bookingRequestDto.phoneNumber())) {
            user = userService.findByPhoneNumber(bookingRequestDto.phoneNumber());
        } else {
            user = userService.registerNewUser(bookingRequestDto.phoneNumber());
        }


        Booking booking = Booking.builder()
                .tour(tour)
                .phoneNumber(bookingRequestDto.phoneNumber())
                .peopleAmount(bookingRequestDto.peopleAmount())
                .bookingDate(LocalDate.now())
                .comment(bookingRequestDto.comment())
                .user(user)
                .build();

        user.addBooking(booking);

        incrementBookingCount(tour.getId());
        bookingRepository.save(booking);
    }
    private void incrementBookingCount(Long tourId){
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new TourNotFoundException("Tour with id: " + tourId + " not found."));
        tour.setBookingCount(tour.getBookingCount() + 1);
        tourRepository.save(tour);
    }

    private void validateBooking(BookingRequestDto bookingRequestDto){
        if(bookingRequestDto.peopleAmount() <= 0 || bookingRequestDto.phoneNumber() == null){
            throw new IllegalArgumentException("Phone number and Number of people must be provided.");
        }
    }

    public void deleteBooking(Long id){
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with id:" + id + " not found."));
        bookingRepository.deleteById(id);
    }

    public List<BookingResponseDto> getAllBookings(){
        List<Booking> allBookings = bookingRepository.findAll();
        if(allBookings == null || allBookings.isEmpty()){
            throw new EmptyListException("There are no bookings available.");
        }
        return bookingMapper.convertToDtoList(allBookings);
    }

}
