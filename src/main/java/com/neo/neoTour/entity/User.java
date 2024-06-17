package com.neo.neoTour.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// USER WITHOUT ROLE
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "is_admin")
    private boolean isAdmin;
    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setUser(this);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        booking.setUser(null);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setUser(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setUser(null);
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}
