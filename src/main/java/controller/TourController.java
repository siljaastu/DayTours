package controller;

import database.TourDB;
import model.Tour;
import model.Traveler;
import model.Booking;

import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Tour Controller.
 */

public class TourController {

    private final TourDB store;

    // def constructor
    public TourController() {
        this.store = new TourDB();
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

    public List<Tour> filterByTimeOfDay(String timeOfDay) {
        if (timeOfDay == null)
            return Collections.emptyList();

        return store.listTours().stream()
                .filter(t -> {
                    String st = t.getStartTime();
                    if (st == null)
                        return false;

                    try {
                        int hour = Integer.parseInt(st.split(":")[0]);

                        switch (timeOfDay.toLowerCase()) {
                            case "morning":
                                return hour < 12;
                            case "afternoon":
                                return hour >= 12 && hour < 18;
                            case "evening":
                                return hour >= 18;
                            default:
                                return false;
                        }
                    } catch (Exception e) {
                        return false;
                    }
                }).collect(Collectors.toList());
    }

    public List<Tour> filterByPrice(int price) {
        return store.listTours().stream()
                .filter(t -> t.getPrice() <= price)
                .collect(Collectors.toList());
    }

    public List<Tour> filterByType(String type) {
        if (type == null)
            return Collections.emptyList();
        return store.filterByType(type);
    }

    public List<Tour> filterByLength(int hours) {
        return store.listTours().stream()
                .filter(t -> t.getLength() <= hours)
                .collect(Collectors.toList());
    }

    // SIMILAR TOURS
    public List<Tour> showSimilarTours(Tour tour) {
        if (tour == null)
            return Collections.emptyList();

        return store.listTours().stream()
                .filter(t -> !t.equals(tour) &&
                        ((t.getTourType() != null &&
                                t.getTourType().equalsIgnoreCase(tour.getTourType()))
                                ||
                                (t.getRegion() != null &&
                                        t.getRegion().equalsIgnoreCase(tour.getRegion()))))
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

    // LIST
    // Returns ALL tours
    public List<Tour> listAllTours() {
        return store.listTours();
    }

    // Get Tour types, for filtering
    public List<String> getAllTourTypes() {
        return store.listTours().stream()
                .map(Tour::getTourType)
                .distinct()
                .sorted()
                .toList();
    }

    // get regions for filtering
    public List<String> getAllRegions() {
        return store.listTours().stream()
                .map(Tour::getRegion)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .toList();
    }

    // List (without duplicates) for ui
    public List<Tour> listUniqueTours() {
        return store.listTours().stream()
                .collect(Collectors.groupingBy(Tour::getTourName))
                .values()
                .stream()
                .map(list -> list.get(0))
                .collect(Collectors.toList());
    }
    // for booking in ui
    public Optional<Tour> findById(String id) {
        return store.findTourById(id);
    }

    // All dep for a selected tour name
    public List<Tour> getDepartures(String tourName) {
        return store.listTours().stream()
                .filter(t -> t.getTourName().equalsIgnoreCase(tourName))
                .sorted(Comparator.comparing(Tour::getDate))
                .collect(Collectors.toList());
    }
}
