package controller;

import database.TourStorageInterface;
import database.StorageMock;
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

    private final database.TourStorageInterface store;

    // def constructor
    public TourController() {
        this.store = new database.StorageMock();
    }

    public TourController(database.TourStorageInterface store) {
        this.store = store;
    }

    // SEARCH.
    // Needs some tweaking, depends on how we do highlights f.ex.
    // Ætti þetta ekki bara að kalla á search aðferðina í TourDB og vinna svo úr
    // þeim gögnum?
    public List<Tour> search(String query) {
        if (query == null || query.trim().isEmpty())
            return Collections.emptyList();
        String q = normalize(query.toLowerCase());

        return store.listTours().stream()
                .filter(t -> (t.getTourName() != null && normalize(t.getTourName()).toLowerCase().contains(q))
                        || (t.getTourType() != null && normalize(t.getTourType()).toLowerCase().contains(q)))
                .collect(Collectors.toList()); // this will give an ArrayList
    }

    // HELPER
    // for special characters, already implemented in Search
    // Could also be used for region etc.
    public static String normalize(String input) {
        if (input == null)
            return "";

        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
    }

    // FILTERS
    // works better as a list you choose from
    // i'll leave it as is until we start working with the real db
    public List<Tour> filterByRegion(String region) {
        if (region == null)
            return Collections.emptyList();

        return store.listTours().stream()
                .filter(t -> t.getRegion() != null &&
                        t.getRegion().equalsIgnoreCase(region))
                .collect(Collectors.toList());
    }

    public List<Tour> filterByDate(Date date) {
        if (date == null)
            return Collections.emptyList();

        return store.listTours().stream()
                .filter(t -> t.getDate() != null && sameDay(t.getDate(), date))
                .collect(Collectors.toList());
    }

    private boolean sameDay(Date a, Date b) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(a);
        Calendar cb = Calendar.getInstance();
        cb.setTime(b);

        return ca.get(Calendar.YEAR) == cb.get(Calendar.YEAR)
                && ca.get(Calendar.DAY_OF_YEAR) == cb.get(Calendar.DAY_OF_YEAR);
    }

    // I had mentioned it might be easier to filter by time-of-day
    // rather than certain time in the day (þeas tími dags > tímasetning)
    // Maybe something like this
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

        return store.listTours().stream()
                .filter(t -> t.getTourType() != null &&
                        t.getTourType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Tour> filterByLength(int hours) {
        return store.listTours().stream()
                .filter(t -> t.getLength() <= hours)
                .collect(Collectors.toList());
    }

    // SIMILAR TOURS - not sure how we want this function to be but this is one idea
    // might be more safe to use tourid instead of equals (which compares objects),
    // not sure if we'd be making more instances of the same tours?
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
    // Haven't done many functions with bookings
    // was a bit back and forth again with the manager dilemma we had
    public Booking book(long tourId, long travelerId, int tickets) {
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

        Booking b = new Booking(0, tourId, travelerId, tickets);
        return store.addBooking(b);
    }

    // LIST
    public List<Tour> listAll() {
        return store.listTours();
    }

    public List<Traveler> listTravelers() {
        return store.listTravelers();
    }
}
