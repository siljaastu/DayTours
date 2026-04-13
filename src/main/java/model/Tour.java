package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Tour model.
 */
public class Tour {
    private String id; // added (not in our design model)
    private String tourName;
    private String tourType;
    private Date date;
    private String region;
    private String startTime;
    private int length;
    private int price;
    private List<String> highlights = new ArrayList<>();
    private String imgUrl; // geri ráð fyrir einni mynd til að eynfalda hlutina aðeins
    private List<String> reviews = new ArrayList<>();
    private int capacity;
    private int minNrTravelers;
    private int nrTravelersBooked; // could rename to nBooked or similar. Just wanted to keep it for now like it's
                                   // in the model
    private boolean isFull;
    private boolean isCancelled;

    // not sure exactly what type of constructors we need so just put few for now
    public Tour() {
    }

    // two-arg constructor
    public Tour(String tourName, String tourType) {
        this.tourName = tourName;
        this.tourType = tourType;
    }

    // Constructor that accepts an id for calls like new Tour(0, "Name", "Type")
    public Tour(String id, String tourName, String tourType) {
        this.id = id;
        this.tourName = tourName;
        this.tourType = tourType;

    }

    // constructor for some fields
    public Tour(String id, String name, String type, String region,
            int price, int length, String startTime, int capacity) {
        this.id = id;
        this.tourName = name;
        this.tourType = type;
        this.region = region;
        this.price = price;
        this.length = length;
        this.startTime = startTime;
        this.capacity = capacity;
        this.nrTravelersBooked = 0;
    }

    // constructor for all fields
    public Tour(String id, String tourName, String tourType, Date date, String region, String startTime,
            int length, int price, String imgUrl, int capacity, int minNrTravelers, int nrTravelersBooked,
            boolean isFull, boolean isCancelled) {
        this.id = id;
        this.tourName = tourName;
        this.tourType = tourType;
        this.date = date;
        this.region = region;
        this.startTime = startTime;
        this.length = length;
        this.price = price;
        // geri ráð fyrir einni mynd núna
        this.imgUrl = imgUrl;
        // Vantar highlights. Þurfum að pæla hvernig við útfærum það.
        this.capacity = capacity;
        this.minNrTravelers = minNrTravelers;
        this.nrTravelersBooked = nrTravelersBooked;
        this.isFull = isFull;
        this.isCancelled = isCancelled;
    }

    // create Tour only with id, name and type
    // sets default values for price, region length, start-time and capacity
    private Tour createTour(String id, String name, String type) {
        Tour t = new Tour(id, name, type);
        t.setPrice(10000);
        t.setRegion("Unknown");
        t.setLength(60);
        t.setStartTime("09:00");
        t.setCapacity(20);
        return t;
    }

    // getters and setters - some probably redundant
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourType() {
        return tourType;
    }

    public void setTourType(String tourType) {
        this.tourType = tourType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // I can't remember exactly what the highlights should show so haven't
    // done much with that. Here it's like tags, but maybe we just want to combine
    // tourType and highlights in some way?
    public List<String> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<String> highlights) {
        this.highlights = highlights;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrls) {
        this.imgUrl = imgUrls;
    }

    // can't remember if we wanted to eliminate this?
    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // we could skip this, many companies do
    public int getMinNrTravelers() {
        return minNrTravelers;
    }

    public void setMinNrTravelers(int minNrTravelers) {
        this.minNrTravelers = minNrTravelers;
    }

    public int getNrTravelersBooked() {
        return nrTravelersBooked;
    }

    public void setNrTravelersBooked(int nrTravelersBooked) {
        this.nrTravelersBooked = nrTravelersBooked;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    public int getRemainingSeats() {
        return capacity - nrTravelersBooked;
    }

    public boolean isFull() {
        return getRemainingSeats() <= 0;
    }

    public void setIsFull(boolean full) {
        isFull = full;
    }

    // Maybe skip reviews?
    public void addReview(String review) {
        if (review != null && !review.trim().isEmpty())
            reviews.add(review.trim());
    }

    public boolean validateAvailability(int tickets) {
        if (tickets <= 0)
            throw new IllegalArgumentException("tickets must be > 0");
        return getRemainingSeats() >= tickets;
    }

    @Override
    public String toString() {
        return "Tour{id=" + id + ", name='" + tourName + "', type='" + tourType + "', remaining=" + getRemainingSeats()
                + "}";
    }
}
