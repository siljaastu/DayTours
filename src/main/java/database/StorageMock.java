package database;

import model.Tour;
import model.Traveler;
import model.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A mock database storage.
 */

public class StorageMock implements database.TourStorageInterface {

    private List<Tour> tours;
    private List<Traveler> travelers;
    private List<Booking> bookings;

    public StorageMock() {
        tours = new ArrayList<>();
        travelers = new ArrayList<>();
        bookings = new ArrayList<>();

        // sample tours
        //(long id, String name, String type, String region, int price, int length, String startTime, int capacity)
        tours.add(new Tour(0, "Northern Lights Tour", "Northern Lights","Reykjavík", 10000, 65,"22:00",15));
        tours.add(new Tour(1, "Golden Circle", "Gullfoss, Geysir, Þingvellir","Þingvellir", 21000, 240,"12:00",8));
        tours.add(new Tour(2, "Whale Watching in Reykjavík", "Whale watching","Reykjavík", 12000, 120,"09:30",20));
        tours.add(new Tour(3, "Puffin Tour", "Puffins, Boat","Reykjavík", 8900, 60,"10:00",25));
        tours.add(new Tour(4,"Boat Tour", "Boat"));

        // sample travelers
        travelers.add(new Traveler(0,"Jóna", "netfang1"));
        travelers.add(new Traveler(1,"Magnús", "netfang2"));
    }

    // Tour ops
    @Override
    public List<Tour> listTours() {
        return new ArrayList<>(tours);
    }

    @Override
    public List<Tour> getToursByKeyword(String keyword) {
        List<Tour> results = new ArrayList<>();
        for (Tour tour : tours) {
            if (tour.getTourName().contains(keyword)) {
                results.add(tour);
            }
        }
        return results;
    }


    @Override
    public Optional<Tour> findTourById(long id) {
        return tours.stream().filter(t -> t.getId() == id).findFirst();
    }

    @Override
    public void updateTour(Tour tour) {
        for (int i = 0; i < tours.size(); i++) {
            if (tours.get(i).getId() == tour.getId()) {
                tours.set(i, tour);
                return;
            }
        }
    }

    @Override
    public Tour addTour(Tour tour) {
        tours.add(tour);
        return tour;
    }

    // Traveler ops
    @Override
    public List<Traveler> listTravelers() {
        return new ArrayList<>(travelers);
    }

    @Override
    public Optional<Traveler> findTraveler(long id) {
        return travelers.stream().filter(t -> t.getId() == id).findFirst();
    }

    @Override
    public Traveler addTraveler(Traveler traveler) {
        travelers.add(traveler);
        return traveler;
    }

    @Override
    public void updateTraveler(Traveler traveler) {
        for (int i = 0; i < travelers.size(); i++) {
            if (travelers.get(i).getId() == traveler.getId()) {
                travelers.set(i, traveler);
                return;
            }
        }
    }

    // Booking ops
    @Override
    public List<Booking> listBookings() {
        return new ArrayList<>(bookings);
    }

    @Override
    public Optional<Booking> findBooking(long id) {
        return bookings.stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    public Booking addBooking(Booking booking) {
        bookings.add(booking);
        return booking;
    }
}