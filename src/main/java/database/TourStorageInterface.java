package database;

import model.Tour;
import model.Traveler;
import model.Booking;

import java.util.List;
import java.util.Optional;

/**
 * TourStorage Interface
 * (originally because I was working with 2 databases, we'll see if we want to keep it)
 */

public interface TourStorageInterface {

    // Tour operations
    List<Tour> listTours();
    List<Tour> getToursByKeyword(String keyword);
    Optional<Tour> findTourById(long id);
    void updateTour(Tour tour);
    Tour addTour(Tour tour);

    // Traveler ops
    List<Traveler> listTravelers();
    Optional<Traveler> findTraveler(long id);
    Traveler addTraveler(Traveler traveler);
    void updateTraveler(Traveler traveler);

    // Booking ops
    List<Booking> listBookings();
    Optional<Booking> findBooking(long id);
    Booking addBooking(Booking booking);
}