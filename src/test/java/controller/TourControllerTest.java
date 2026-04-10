package controller;

import model.Tour;
import database.StorageMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test fixture for TourController.
 * This class tests the controller logic by using StorageMock,
 * a mock object implemented to simulate a storage component.
 */
class TourControllerTest {

    private controller.TourController controller;

    /**
     * Setup method that runs before each test.
     * Initializes the controller with StorageMock object.
     */
    @BeforeEach
    void setUp() {
        controller = new controller.TourController(new StorageMock());
    }

    /**
     * Tear down method that runs after each test. Sets controller to null.
     */
    @AfterEach
    void tearDown() {
        controller = null;
    }

    // SEARCH TESTS

    /**
     * Test that a valid keyword returns matching tours.
     */
    @Test
    @DisplayName("Search finds Northern Lights tour")
    void testSearchFindsMatch() {
        List<Tour> results = controller.search("Northern Lights");
        assertEquals(1, results.size());
        assertEquals("Northern Lights Tour", results.get(0).getTourName());
    }

    /**
     * Test that search is case-insensitive.
     */
    @Test
    @DisplayName("Search is case-insensitive")
    void testCaseInsensitiveSearch() {
        List<Tour> results = controller.search("northern lights");
        assertEquals(1, results.size());
        assertEquals("Northern Lights Tour", results.get(0).getTourName());
    }

    /**
     * Test that search is accent-insensitive.
     */
    @Test
    @DisplayName("Search is accent-insensitive")
    void testAccentInsensitiveSearch() {
        List<Tour> results = controller.search("Reykjavik");
        assertEquals(1, results.size());
        assertEquals("Whale Watching in Reykjavík", results.get(0).getTourName());
    }

    /**
     * Test that no results are returned when no match is found.
     */
    @Test
    @DisplayName("Search returns empty when no match is found")
    void testNoMatch() {
        List<Tour> results = controller.search("Fly");
        assertTrue(results.isEmpty());
    }

    /**
     * Test that all relevant results are shown for a keyword.
     */
    @Test
    @DisplayName("Search shows all results, also when there are multiples")
    void testMultipleResults() {
        List<Tour> results = controller.search("Boat");
        assertEquals(2, results.size());
    }
}