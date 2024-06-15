package com.neo.neoTour.service;

import com.neo.neoTour.dto.TourListDto;
import com.neo.neoTour.dto.TourRequestDto;
import com.neo.neoTour.dto.TourResponseDto;
import com.neo.neoTour.entity.Image;
import com.neo.neoTour.entity.Location;
import com.neo.neoTour.entity.Tour;
import com.neo.neoTour.exception.EmptyListException;
import com.neo.neoTour.exception.TourNotFoundException;
import com.neo.neoTour.mapper.TourMapper;
import com.neo.neoTour.repository.ImageRepository;
import com.neo.neoTour.repository.TourRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class TourService {
    //inject dependency
    private final TourRepository tourRepository;
    private final TourMapper tourMapper;
    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;


    // ***************************************************************************
    //                                ADMIN CONTROLLER
    // ***************************************************************************
    public List<TourResponseDto> findAllTours(){
        List<Tour> allTours = tourRepository.findAll(); // get List<Tour> from DB
        if (allTours == null || allTours.isEmpty()) {
            throw new EmptyListException("There are no tours available.");
        }
        return tourMapper.convertToDtoList(allTours); // List<Tour> --> List<TourSimpleDto>
    }

    public TourResponseDto getTourById(Long id){
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new TourNotFoundException("Tour with id: " + id + "not found."));
        return tourMapper.convertToDto(tour);
    }

    public void createTour(TourRequestDto tourRequestDto,
                           MultipartFile image){
        if (tourRequestDto == null || image == null) {
            throw new IllegalArgumentException("RequestDto and image are required.");
        }
        //set basic tour data
        Tour tour = new Tour();
        tour.setName(tourRequestDto.name());
        tour.setDescription(tourRequestDto.description());
        tour.setRecommendedSeasons(tourRequestDto.recommendedSeasons());
        //set tour location
        Location location = new Location();
        location.setPlace(tourRequestDto.place());
        location.setCountry(tourRequestDto.country());
        location.setContinent(tourRequestDto.continent());
        tour.setLocation(location);
        //process an image
        try {
            // Upload new image to Cloudinary
            Image tourImage = new Image();
            tourImage.setImageUrl(cloudinaryService.uploadFile(image, "tourImages"));
            imageRepository.save(tourImage);
            tour.setImage(tourImage);
        } catch (Exception e) {
            throw new RuntimeException("Image upload failed: " + e.getMessage());
        }

        tourRepository.save(tour);
    }

    public TourResponseDto updateTour(TourRequestDto tourRequestDto,
                                      MultipartFile image, Long id){
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new TourNotFoundException("Tour with id: " + id + " not found."));
        tour.setName(tourRequestDto.name());
        tour.setDescription(tourRequestDto.description());

        Location location = tour.getLocation();
        if (location == null) {
            location = new Location();
        }
        location.setPlace(tourRequestDto.place());
        location.setCountry(tourRequestDto.country());
        location.setContinent(tourRequestDto.continent());
        tour.setLocation(location);

        if (image != null && !image.isEmpty()) {
            try {
                // Upload new image to Cloudinary
                Image tourImage = new Image();
                tourImage.setImageUrl(cloudinaryService.uploadFile(image, "tourImages"));
                imageRepository.save(tourImage);

                // Set the new image to the tour
                tour.setImage(tourImage);
            } catch (Exception e) {
                throw new RuntimeException("Image upload failed: " + e.getMessage());
            }
        }

        return tourMapper.convertToDto(tourRepository.save(tour));
    }

    public void deleteById(Long id){
        tourRepository.findById(id)
                .orElseThrow(() -> new TourNotFoundException("Tour with id: " + id + " not found."));
        tourRepository.deleteById(id);
    }







    // ***************************************************************************
    //                               USER CONTROLLER
    // ***************************************************************************
    public TourResponseDto findTourById(Long id){
        Tour tour = tourRepository.findById(id).orElseThrow(() -> new TourNotFoundException("Tour with id: " + id + " not found."));
        incrementTourViews(id);
        return tourMapper.convertToDto(tour);
    }

    public void incrementTourViews(Long id){
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new TourNotFoundException("Tour with id: " + id + " not found."));
        tour.setViewCount(tour.getViewCount() + 1);
        tourRepository.save(tour);
    }

    public List<TourListDto> findFeaturedTours(Pageable pageable){
        List<Tour> featuredTours = tourRepository.findFeaturedTours(pageable).getContent();
        if(featuredTours == null || featuredTours.isEmpty()){
            throw new EmptyListException("There are no new tours available.");
        }
        return tourMapper.convertToListOfDto(featuredTours);
    }

    public List<TourListDto> findPopularTours(Pageable pageable){
        List<Tour> popularTours = tourRepository.findPopularTours(pageable).getContent();
        if(popularTours == null || popularTours.isEmpty()){
            throw new EmptyListException("There are no popular tours available.");
        }
        return tourMapper.convertToListOfDto(popularTours);
    }

    // PS: rn the number of bookings is 0 for every Tour !!!
    // test just on response of EmptyListException
    public List<TourListDto> findMostVisitedTours(Pageable pageable){
        List<Tour> mostVisitedTours = tourRepository.findMostVisitedTours(pageable).getContent();
        if(mostVisitedTours == null || mostVisitedTours.isEmpty()){
            throw new EmptyListException("There are no most visited tours available.");
        }
        return tourMapper.convertToListOfDto(mostVisitedTours);
    }

    public List<TourListDto> findAsianTours(){
        List<Tour> asianTours = tourRepository.findAsianTours();
        if(asianTours == null || asianTours.isEmpty()){
            throw new EmptyListException("There are no Asian tours available.");
        }
        return tourMapper.convertToListOfDto(asianTours);
    }

    public List<TourListDto> findEuropeanTours(){
        List<Tour> europeanTours = tourRepository.findEuropeanTours();
        if(europeanTours == null || europeanTours.isEmpty()){
            throw new EmptyListException("There are no European tours available.");
        }
        return tourMapper.convertToListOfDto(europeanTours);
    }

    public List<TourListDto> findRecommendedTours(){
        List<Tour> recommendedTours = filterRecommendedTours();
        if(recommendedTours.isEmpty()){
            throw new EmptyListException("There are no recommended tours available.");
        }
        return tourMapper.convertToListOfDto(recommendedTours);
    }

    private List<Tour> filterRecommendedTours() {
        int currentMonth = LocalDate.now().getMonthValue();
        String season;
        switch (currentMonth) {
            case 12:
            case 1:
            case 2:
                season = "winter";
                break;
            case 3:
            case 4:
            case 5:
                season = "spring";
                break;
            case 6:
            case 7:
            case 8:
                season = "summer";
                break;
            case 9:
            case 10:
            case 11:
                season = "autumn";
                break;
            default:
                return Collections.emptyList();
        }
        return tourRepository.findToursBySeason(season);
    }


}
