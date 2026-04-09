package database;

import model.Tour;
import model.Traveler;
import model.Booking;
import java.sql.*;
import java.util.ArrayList;

public class TourDB {
    private String url = "jdbc:sqlite:tours.db";

    // Helper method to open connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public ArrayList<Tour> select(String keyword) {
        ArrayList<Tour> results = new ArrayList<>();
        String query = "SELECT * FROM tours WHERE name LIKE ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Tour tour = new Tour();
                tour.setId(rs.getInt("id"));
                tour.setTourName(rs.getString("name"));
                tour.setTourType(rs.getString("type"));
                tour.setDate(rs.getDate("day"));
                tour.setStartTime(rs.getString("startTime"));
                tour.setLength(rs.getInt("duration"));
                tour.setRegion(rs.getString("region"));
                // tour.setStartLocation(rs.getString("leavesFrom"));
                tour.setPrice(rs.getInt("price"));
                tour.setImgUrl(rs.getString("imgUrl"));
                tour.setCapacity(rs.getInt("capacity"));
                // tour.setMinTravelers(rs.getInt("minNrTravelers"));
                tour.setNrTravelersBooked(rs.getInt("nrBookings"));
                tour.setIsFull(rs.getBoolean("full"));
                tour.setIsCancelled(rs.getBoolean("cancelled"));
                results.add(tour);

            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();

        }

    }

    public static void main(String[] args)
            throws Exception {

    }

}
