package com.neo.neoTour.mapper;

import com.neo.neoTour.dto.ReviewResponseDto;
import com.neo.neoTour.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReviewMapper {
    public ReviewResponseDto convertToDto(Review review){
        return new ReviewResponseDto(
                review.getId(),
                review.getUser().getEmail(),
                review.getReviewText(),
                review.getReviewDate()
        );
    }

    public List<ReviewResponseDto> convertToDtoList(List<Review> reviews){
        return reviews.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
