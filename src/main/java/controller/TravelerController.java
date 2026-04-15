package controller;

import database.TourStorageInterface;
import model.Traveler;

import java.util.List;
import java.util.Optional;

/**
 * Controller for traveler-related ops.
 * I'm not sure to which extent we actually need a controller for travelers.
 * Mostly depends on if we're implementing addToFav.
 * But for now I'm assuming we have some sort of traveler db as well.
 * 
 * TRAVELER ER BARA Í SAMA DB OG ALLT ANNAÐ. EN ÞAÐ ÞARF AÐ KALLA Á
 * ADDTRAVELER(TRAVELER TRAVELER) TIL AÐ GETA BÆTT HONUM INN Í GAGNABANKANN :)
 * SVO ÞARF AÐ KALLA Á BOOK(TOUR TOUR, TRAVELER TRAVELER, BOOKING BOOKING) TIL
 * ÞESS AÐ BÆTA BOOKING VIÐ Í GAGNASAFNIÐ :)
 */
public class TravelerController {

    private final TourStorageInterface store;

    // Constructor that accepts any store
    public TravelerController(TourStorageInterface store) {
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
    public Optional<Traveler> findById(long id) {
        return store.findTraveler(id);
    }

    /**
     * Create a new traveler.
     */
    public Traveler createTraveler(Traveler t) {
        return store.addTraveler(t);
    }

    /**
     * Add to favorites skeleton, not sure if we want to implement
     */
    public Traveler addToFavorites(long travelerId, long tourid) {
        Traveler tr = store.findTraveler(travelerId)
                .orElseThrow(() -> new IllegalArgumentException("Traveler not found"));
        tr.addToFavorites(tourid);
        store.updateTraveler(tr);
        return tr;
    }
}