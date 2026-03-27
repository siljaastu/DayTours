package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Traveler model. Traveler info and maybe favorites?
 */
public class Traveler {
    private long id;
    private String name;
    private String email;
    private Set<Long> favorites = new HashSet<>(); // to avoid duplicates
    public Traveler() {}

    public Traveler(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<Long> getFavorites() { return favorites; }
    public void setFavorites(Set<Long> favorites) { this.favorites = favorites; }

    /**
     * Moved this from Tour (in design model)
     */
    public void addToFavorites(long tourId) {
        favorites.add(tourId);
    }

    @Override
    public String toString() {
        return "Traveler{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
