package com.neo.neoTour.mapper;

import com.neo.neoTour.dto.TourResponseDto;
import com.neo.neoTour.dto.UserResponseDto;
import com.neo.neoTour.entity.Role;
import com.neo.neoTour.entity.Tour;
import com.neo.neoTour.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ReviewMapper reviewMapper;
    private final BookingMapper bookingMapper;

    public UserResponseDto convertToDto(User user){
        String roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.joining(", "));

        return new UserResponseDto(
                user.getId(),
                user.getPhoneNumber(),
                roles,
                user.getReviews().stream().map(reviewMapper::convertToDto).toList(),
                user.getBookings().stream().map(bookingMapper::convertToDto).toList()
        );
    }

    public List<UserResponseDto> convertToDtoList(List<User> users){
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
