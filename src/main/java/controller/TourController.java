package controller;

import database.TourDB;
import model.Tour;
import model.Traveler;
import model.Booking;

import java.awt.print.Book;
import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Tour Controller.
 */

public class TourController {

    private final TourDB store;

    // def constructor
    public TourController(TourDB tourDB) {
        this.store = tourDB;
    }

    // SEARCH
    public List<Tour> search(String query) {
        if (query == null || query.trim().isEmpty())
            return Collections.emptyList();
        String q = normalize(query.toLowerCase());
        return store.search(q);
    }

    // HELPER (for special characters in icelandic)
    public static String normalize(String input) {
        if (input == null)
            return "";
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
    }

    // FILTERS
    public List<Tour> filterByRegion(String region) {
        if (region == null)
            return Collections.emptyList();
        return store.filterByRegion(region);
    }

    public List<Tour> filterByDate(String date) {
        if (date == null)
            return Collections.emptyList();
        return store.filterByDate(date);
    }

    private boolean sameDay(Date a, Date b) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(a);
        Calendar cb = Calendar.getInstance();
        cb.setTime(b);

        return ca.get(Calendar.YEAR) == cb.get(Calendar.YEAR)
                && ca.get(Calendar.DAY_OF_YEAR) == cb.get(Calendar.DAY_OF_YEAR);
    }

    public List<Tour> filterByType(String type) {
        if (type == null)
            return Collections.emptyList();
        return store.filterByType(type);
    }

    // this should be done in TourDB if we want to keep it
    public List<Tour> filterByLength(int hours) {
        return store.listTours().stream()
                .filter(t -> t.getLength() <= hours)
                .collect(Collectors.toList());
    }


    // BOOKING
    public Booking book(String tourId, int travelerId, int tickets) {
        if (tickets <= 0)
            throw new IllegalArgumentException("tickets must be > 0");

        Tour tour = store.findTourById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("tour not found"));

        Traveler traveler = store.findTraveler(travelerId)
                .orElseThrow(() -> new IllegalArgumentException("traveler not found"));

        if (!tour.validateAvailability(tickets)) {
            throw new IllegalArgumentException("not enough seats available");
        }

        // update booked seats
        tour.setNrTravelersBooked(tour.getNrTravelersBooked() + tickets);
        store.updateTour(tour);

        Booking b = new Booking(tourId, travelerId, tickets);
        store.book(b);
        return b;
    }

    public Booking bookWithNewTraveler(String tourId, String name, String phone, String email, int tickets) {
        if (tourId == null || tourId.isBlank()) {
            throw new IllegalArgumentException("Tour ID is required.");
        }
    
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }
    
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone number is required.");
        }
    
        if (tickets <= 0) {
            throw new IllegalArgumentException("Number of tickets must be positive.");
        }
    
        Tour tour = store.findTourById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour not found."));
    
        if (!tour.validateAvailability(tickets)) {
            throw new IllegalArgumentException("Not enough seats available.");
        }
    
        Traveler traveler = new Traveler();
        traveler.setName(name);
        traveler.setPhoneNr(phone);
        traveler.setEmail(email);
    
        int travelerId = store.addTraveler(traveler);
        traveler.setId(travelerId);
    
        tour.setNrTravelersBooked(tour.getNrTravelersBooked() + tickets);
        store.updateTour(tour);
    
        Booking booking = new Booking(tourId, travelerId, tickets);
        store.book(booking);
    
        return booking;
    }

    // LIST
    // Returns ALL tours
    public List<Tour> listAllTours() {
        return store.listTours();
    }

    // Get Tour types, for filtering
    public List<String> getAllTourTypes() {
        return store.getDistinctTypes();
    }


    // get regions for filtering
    public List<String> getAllRegions() {
        return store.getDistinctRegions();
    }

    // List (without duplicates) for ui
    public List<Tour> listUniqueTours() {
        return store.listUniqueTours();
    }
    // for booking in ui
    public Optional<Tour> findById(String id) {
        return store.findTourById(id);
    }
    public List<Booking> listBookings(){
        return store.listBookings();
    }

}
