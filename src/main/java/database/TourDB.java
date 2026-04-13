package database;

import model.Tour;
import model.Traveler;
import model.Booking;
import java.sql.*;
import java.util.ArrayList;

public class TourDB {
    private String url = "jdbc:sqlite:src/main/java/database/tours.db";

    // Helper method to open connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }

    /**
     * Helper function to make Tour objects.
     * 
     * @param rs the line from the SQL query
     * @return Tour object.
     * @throws SQLException
     */
    private Tour mapResultSetToTour(ResultSet rs) throws SQLException {
        Tour tour = new Tour();
        tour.setId(rs.getString("id"));
        tour.setTourName(rs.getString("name"));
        tour.setTourType(rs.getString("type"));
        String dateString = rs.getString("day");
        if (dateString != null) {
            tour.setDate(java.sql.Date.valueOf(dateString));
        }
        tour.setStartTime(rs.getString("startTime"));
        tour.setLength(rs.getInt("duration"));
        tour.setRegion(rs.getString("region"));
        tour.setPrice(rs.getInt("price"));
        tour.setImgUrl(rs.getString("imgUrl"));
        tour.setCapacity(rs.getInt("capacity"));
        tour.setNrTravelersBooked(rs.getInt("nrBookings"));
        tour.setIsFull(rs.getBoolean("full"));
        tour.setIsCancelled(rs.getBoolean("cancelled"));

        return tour;
    }

    /**
     * Looks up tours in DB based on a keyword and returns all
     * tours that have that keyword in the title.
     *
     * @param keyword The search parameter
     * @return All tours that have the keyword in their title
     * @throws SQLeseption
     */

    public ArrayList<Tour> search(String keyword) {
        ArrayList<Tour> results = new ArrayList<>();
        String query = "SELECT * FROM tours WHERE name LIKE ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                results.add(mapResultSetToTour(rs));

            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();

        }

    }

    public ArrayList<Tour> filterByRegion(String region) {
        ArrayList<Tour> results = new ArrayList<>();
        String query = "SELECT * FROM tours WHERE region = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, region);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                results.add(mapResultSetToTour(rs));
            }

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();

        }
    }

    public static void main(String[] args)
            throws Exception {

        TourDB prufa = new TourDB();
        ArrayList<Tour> tours = new ArrayList<>(prufa.search("reykjavík"));
        for (Tour tour : tours) {
            System.out.println(tour.getTourName() + " " + tour.getTourType() + " " + tour.getPrice());
        }

    }

}
