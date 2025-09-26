package com.example.booking_agency.models;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable {
    private String id;
    private String name;
    private String description;
    private String type; // SINGLE, DOUBLE, SUITE, DELUXE, PRESIDENTIAL
    private double pricePerNight;
    private int maxOccupancy;
    private String hotelId;
    private String hotelName;
    private String hotelImage;
    private double rating;
    private int reviewCount;
    private String location;
    private String address;
    private double distance;
    private List<String> amenities;
    private List<String> images;
    private boolean isAvailable;
    private String checkInTime;
    private String checkOutTime;
    private int roomNumber;
    private int floorNumber;
    private double roomSize; // in square meters
    private String bedType; // SINGLE, DOUBLE, QUEEN, KING
    private boolean hasBalcony;
    private boolean hasSeaView;
    private boolean hasCityView;
    private boolean hasWifi;
    private boolean hasAirConditioning;
    private boolean hasBreakfast;
    private String cancellationPolicy;

    public enum RoomType {
        SINGLE, DOUBLE, SUITE, DELUXE, PRESIDENTIAL
    }

    public enum BedType {
        SINGLE, DOUBLE, QUEEN, KING
    }

    public Room() {}

    public Room(String id, String name, String description, String type, double pricePerNight, int maxOccupancy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.maxOccupancy = maxOccupancy;
        this.isAvailable = true;
        this.checkInTime = "14:00";
        this.checkOutTime = "11:00";
        this.hasWifi = true;
        this.hasAirConditioning = true;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }

    public int getMaxOccupancy() { return maxOccupancy; }
    public void setMaxOccupancy(int maxOccupancy) { this.maxOccupancy = maxOccupancy; }

    public String getHotelId() { return hotelId; }
    public void setHotelId(String hotelId) { this.hotelId = hotelId; }

    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }

    public String getHotelImage() { return hotelImage; }
    public void setHotelImage(String hotelImage) { this.hotelImage = hotelImage; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public int getReviewCount() { return reviewCount; }
    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    public List<String> getAmenities() { return amenities; }
    public void setAmenities(List<String> amenities) { this.amenities = amenities; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public String getCheckInTime() { return checkInTime; }
    public void setCheckInTime(String checkInTime) { this.checkInTime = checkInTime; }

    public String getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(String checkOutTime) { this.checkOutTime = checkOutTime; }

    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public int getFloorNumber() { return floorNumber; }
    public void setFloorNumber(int floorNumber) { this.floorNumber = floorNumber; }

    public double getRoomSize() { return roomSize; }
    public void setRoomSize(double roomSize) { this.roomSize = roomSize; }

    public String getBedType() { return bedType; }
    public void setBedType(String bedType) { this.bedType = bedType; }

    public boolean isHasBalcony() { return hasBalcony; }
    public void setHasBalcony(boolean hasBalcony) { this.hasBalcony = hasBalcony; }

    public boolean isHasSeaView() { return hasSeaView; }
    public void setHasSeaView(boolean hasSeaView) { this.hasSeaView = hasSeaView; }

    public boolean isHasCityView() { return hasCityView; }
    public void setHasCityView(boolean hasCityView) { this.hasCityView = hasCityView; }

    public boolean isHasWifi() { return hasWifi; }
    public void setHasWifi(boolean hasWifi) { this.hasWifi = hasWifi; }

    public boolean isHasAirConditioning() { return hasAirConditioning; }
    public void setHasAirConditioning(boolean hasAirConditioning) { this.hasAirConditioning = hasAirConditioning; }

    public boolean isHasBreakfast() { return hasBreakfast; }
    public void setHasBreakfast(boolean hasBreakfast) { this.hasBreakfast = hasBreakfast; }

    public String getCancellationPolicy() { return cancellationPolicy; }
    public void setCancellationPolicy(String cancellationPolicy) { this.cancellationPolicy = cancellationPolicy; }

    // Helper methods
    public String getFormattedPrice() {
        return String.format("$%.0f/night", pricePerNight);
    }

    public String getOccupancyText() {
        return maxOccupancy + " guest" + (maxOccupancy > 1 ? "s" : "");
    }

    public String getRoomSizeText() {
        return roomSize > 0 ? String.format("%.0f m²", roomSize) : "";
    }
}
