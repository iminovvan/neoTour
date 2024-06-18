package com.neo.neoTour.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private Long id;
    @Column(name = "tour_name")
    private String name;
    @Column(name = "tour_description")
    private String description;
    @Column(name = "booking_count")
    private int bookingCount;
    @Column(name = "view_count")
    private int viewCount;
    @Column(name = "recommended_seasons")
    private String recommendedSeasons; // recommended sorting
    @CreationTimestamp
    private LocalDateTime createdDate;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;
}



