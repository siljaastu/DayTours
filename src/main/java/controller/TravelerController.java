package controller;
import database.TourDB;
import model.Traveler;
import java.util.List;
import java.util.Optional;

/**
 * Controller for traveler-related ops.
 */
public class TravelerController {

    private final TourDB store;

    // Constructor that accepts any store
    public TravelerController(TourDB store) {
        if (store == null)
            throw new IllegalArgumentException("store cannot be null");
        this.store = store;
    }

    /**
     * Show all travelers.
     */
    public List<Traveler> listAll() {
        return store.listTravelers();
    }

    /**
     * Find a traveler by id.
     */
    public Optional<Traveler> findById(int id) {
        return store.findTraveler(id);
    }


    public int createTraveler(Traveler t) {
        return store.addTraveler(t);
    }

    /**
     * Not implemented. Saved for future implementation.
     * Add to favorites skeleton.
     */
//    public Traveler addToFavorites(long travelerId, long tourid) {
//        Traveler tr = store.findTraveler(travelerId)
//                .orElseThrow(() -> new IllegalArgumentException("Traveler not found"));
//        tr.addToFavorites(tourid);
//        store.updateTraveler(tr);
//        return tr;
//    }
}
