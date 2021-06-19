package models;

import enums.ManifestationType;

import java.util.Date;

public class Manifestation {
    private String name;
    private ManifestationType manifestationType;
    private int capacity;
    private Date manifestationDateTime;
    private double priceOfRegularTicket;
    private boolean isActive;
    private Location location;
    private String posterPath;

    public Manifestation() {
    }

    public Manifestation(String name, ManifestationType manifestationType, int capacity,  Date manifestationDateTime, double priceOfRegularTicket, boolean isActive, Location location, String posterPath) {
        this.name = name;
        this.manifestationType = manifestationType;
        this.capacity = capacity;
        this.manifestationDateTime = manifestationDateTime;
        this.priceOfRegularTicket = priceOfRegularTicket;
        this.isActive = isActive;
        this.location = location;
        this.posterPath = posterPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ManifestationType getManifestationType() {
        return manifestationType;
    }

    public void setManifestationType(ManifestationType manifestationType) {
        this.manifestationType = manifestationType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getManifestationDateTime() {
        return manifestationDateTime;
    }

    public void setManifestationDateTime(Date manifestationDateTime) {
        this.manifestationDateTime = manifestationDateTime;
    }

    public double getPriceOfRegularTicket() {
        return priceOfRegularTicket;
    }

    public void setPriceOfRegularTicket(double priceOfRegularTicket) {
        this.priceOfRegularTicket = priceOfRegularTicket;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
