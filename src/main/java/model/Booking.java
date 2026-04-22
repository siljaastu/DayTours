package model;

import java.util.Date;

/**
 * Booking model.
 */
public class Booking {
    private String tourId;
    private int travelerId;
    private int tickets;
    private String status = "CONFIRMED";
    private Date createdAt = new Date();

    public Booking() {
    }

    public Booking(String tourId, int travelerId, int tickets) {
        this.tourId = tourId;
        this.travelerId = travelerId;
        this.tickets = tickets;
    }


    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public int getTravelerId() {
        return travelerId;
    }

    public void setTravelerId(int travelerId) {
        this.travelerId = travelerId;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    /*
    // Not implemented. Saved for future implementation.
    public void cancel() {
        this.status = "CANCELLED";
    }
    */

    @Override
    public String toString() {
        return "Booking{tourId=" + tourId + ", travelerId=" + travelerId + ", tickets=" + tickets
                + ", status=" + status + "}";
    }
}
