package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Traveler model
 */
public class Traveler {
    private int id;
    private String name;
    private String email;
    private String phoneNR;
    private Set<Long> favorites = new HashSet<>(); // to avoid duplicates

    public Traveler() {
    }

    public Traveler(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNr() {
        return phoneNR;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNR = phoneNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Long> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Long> favorites) {
        this.favorites = favorites;
    }

    public void addToFavorites(long tourId) {
        favorites.add(tourId);
    }

    @Override
    public String toString() {
        return "Traveler{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
