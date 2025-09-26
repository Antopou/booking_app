package com.example.booking_agency.models;

import java.io.Serializable;
import java.util.List;

public class Service implements Serializable {
    private String id;
    private String name;
    private String description;
    private String category;
    private double price;
    private int duration; // in minutes
    private String providerId;
    private String providerName;
    private String providerImage;
    private double rating;
    private int reviewCount;
    private String location;
    private double distance;
    private List<String> availableTimeSlots;
    private List<String> images;
    private boolean isAvailable;

    public Service() {}

    public Service(String id, String name, String description, String category, double price, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.duration = duration;
        this.isAvailable = true;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getProviderId() { return providerId; }
    public void setProviderId(String providerId) { this.providerId = providerId; }

    public String getProviderName() { return providerName; }
    public void setProviderName(String providerName) { this.providerName = providerName; }

    public String getProviderImage() { return providerImage; }
    public void setProviderImage(String providerImage) { this.providerImage = providerImage; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getReviewCount() { return reviewCount; }
    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    public List<String> getAvailableTimeSlots() { return availableTimeSlots; }
    public void setAvailableTimeSlots(List<String> availableTimeSlots) { this.availableTimeSlots = availableTimeSlots; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}
