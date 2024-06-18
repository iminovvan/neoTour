package com.neo.neoTour.repository;

import com.neo.neoTour.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
