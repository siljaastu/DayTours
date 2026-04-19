package database;

import model.Tour;
import model.Traveler;
import model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

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
        tour.setTourID(rs.getString("id"));
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public ArrayList<Tour> listTours() {
        ArrayList<Tour> results = new ArrayList<>();
        String query = "SELECT * FROM Tours";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                results.add(mapResultSetToTour(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
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
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return results;
    }

    public ArrayList<Tour> filterByType(String type) {
        ArrayList<Tour> results = new ArrayList<>();
        String query = "SELECT * FROM tours WHERE type = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, type);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                results.add(mapResultSetToTour(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return results;
    }

    public ArrayList<Tour> filterByDate(String date) {
        ArrayList<Tour> results = new ArrayList<>();
        String query = "SELECT * FROM tours WHERE day = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, date);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                results.add(mapResultSetToTour(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public ArrayList<Tour> listUniqueTours() {
        ArrayList<Tour> results = new ArrayList<>();
        String query = "SELECT * FROM Tours GROUP BY name";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) results.add(mapResultSetToTour(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public ArrayList<String> getDistinctRegions() {
        ArrayList<String> results = new ArrayList<>();
        String query = "SELECT DISTINCT region FROM Tours WHERE region IS NOT NULL ORDER BY region";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                results.add(rs.getString("region"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public ArrayList<String> getDistinctTypes() {
        ArrayList<String> results = new ArrayList<>();
        String query = "SELECT DISTINCT type FROM Tours WHERE type IS NOT NULL ORDER BY type";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                results.add(rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }




    public boolean addTraveler(Traveler traveler) {
        boolean travelerAdded = false;
        String query = "INSERT OR IGNORE INTO Travelers(id, name, phoneNR, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, traveler.getId());
            pstmt.setString(2, traveler.getName());
            pstmt.setString(3, traveler.getPhoneNr());
            pstmt.setString(4, traveler.getEmail());

            pstmt.executeUpdate();

            travelerAdded = true;

        } catch (SQLException e) {
            System.err.println("Error saving traveler: " + e.getMessage());
        }

        return travelerAdded;
    }

    /**
     * Takes in tour, traveler and booking.
     * 
     * @param tour
     * @param traveler
     * @param booking
     * @return True or false based on if adding into database was a success
     */
    public boolean book(Booking booking) {
        boolean booked = false;
        String query = "INSERT INTO Bookings(travelerId, tourId, nrTickets, bookingStatus, createdAt) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, booking.getTravelerId());
            pstmt.setString(2, booking.getTourId());
            pstmt.setInt(3, booking.getTickets());
            pstmt.setString(4, booking.getStatus());
            pstmt.setDate(5, new java.sql.Date(booking.getCreatedAt().getTime()));
            pstmt.executeUpdate();

            booked = true;

        } catch (SQLException e) {
            System.err.println("Error booking: " + e.getMessage());
        }
        return booked;
    }

    public void updateTour(Tour tour) {
        String query = """
        UPDATE Tours 
        SET nrBookings = ?, full = ?
        WHERE id = ?
    """;

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, tour.getNrTravelersBooked());
            pstmt.setBoolean(2, tour.isFull());
            pstmt.setString(3, tour.getTourID());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Traveler> listTravelers() {
        ArrayList<Traveler> results = new ArrayList<>();
        String query = "SELECT * FROM Travelers";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Traveler t = new Traveler();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setPhoneNr(rs.getString("phoneNR"));
                t.setEmail(rs.getString("email"));

                results.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    public Optional<Traveler> findTraveler(int id) {
        String query = "SELECT * FROM Travelers WHERE id = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Traveler t = new Traveler();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setPhoneNr(rs.getString("phoneNR"));
                t.setEmail(rs.getString("email"));
                return Optional.of(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
    public ArrayList<Booking> listBookings() {
        ArrayList<Booking> results = new ArrayList<>();
        String query = "SELECT * FROM Bookings";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Booking b = new Booking(
                        rs.getString("tourId"),
                        rs.getInt("travelerId"),
                        rs.getInt("nrTickets")
                );

                b.setStatus(rs.getString("bookingStatus"));
                b.setCreatedAt(rs.getDate("createdAt"));

                results.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    public Optional<Tour> findTourById(String id) {
        String query = "SELECT * FROM Tours WHERE id = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToTour(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public static void main(String[] args)
            throws Exception {

        TourDB prufa2 = new TourDB();
        ArrayList<Tour> tours2 = new ArrayList<>(prufa2.filterByType("Northern Lights"));
        for (Tour tour : tours2) {
            System.out.println(tour.getTourName() + " " + tour.getTourType() + " " + tour.getPrice());
        }

    }

}
