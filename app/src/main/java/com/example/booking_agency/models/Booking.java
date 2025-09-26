package com.example.booking_agency.models;

import java.io.Serializable;

public class Booking implements Serializable {
    private String id;
    private String userId;
    private String roomId;
    private String roomName;
    private String hotelName;
    private String hotelImage;
    private String checkInDate;
    private String checkOutDate;
    private String checkInTime;
    private String checkOutTime;
    private int numberOfNights;
    private int numberOfGuests;
    private double pricePerNight;
    private double totalPrice;
    private String status; // PENDING, CONFIRMED, COMPLETED, CANCELLED
    private String notes;
    private String createdAt;
    private String updatedAt;
    private String location;
    private String address;
    private String roomType;
    private String bedType;
    private boolean hasBreakfast;
    private String cancellationPolicy;

    public enum BookingStatus {
        PENDING, CONFIRMED, COMPLETED, CANCELLED
    }

    public Booking() {}

    public Booking(String id, String userId, String roomId, String roomName, 
                   String checkInDate, String checkOutDate, int numberOfNights, double pricePerNight) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.roomName = roomName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfNights = numberOfNights;
        this.pricePerNight = pricePerNight;
        this.totalPrice = pricePerNight * numberOfNights;
        this.status = BookingStatus.PENDING.toString();
        this.createdAt = String.valueOf(System.currentTimeMillis());
        this.checkInTime = "14:00";
        this.checkOutTime = "11:00";
        this.numberOfGuests = 1;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }

    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }

    public String getHotelImage() { return hotelImage; }
    public void setHotelImage(String hotelImage) { this.hotelImage = hotelImage; }

    public String getCheckInDate() { return checkInDate; }
    public void setCheckInDate(String checkInDate) { this.checkInDate = checkInDate; }

    public String getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(String checkOutDate) { this.checkOutDate = checkOutDate; }

    public String getCheckInTime() { return checkInTime; }
    public void setCheckInTime(String checkInTime) { this.checkInTime = checkInTime; }

    public String getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(String checkOutTime) { this.checkOutTime = checkOutTime; }

    public int getNumberOfNights() { return numberOfNights; }
    public void setNumberOfNights(int numberOfNights) { this.numberOfNights = numberOfNights; }

    public int getNumberOfGuests() { return numberOfGuests; }
    public void setNumberOfGuests(int numberOfGuests) { this.numberOfGuests = numberOfGuests; }

    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public String getBedType() { return bedType; }
    public void setBedType(String bedType) { this.bedType = bedType; }

    public boolean isHasBreakfast() { return hasBreakfast; }
    public void setHasBreakfast(boolean hasBreakfast) { this.hasBreakfast = hasBreakfast; }

    public String getCancellationPolicy() { return cancellationPolicy; }
    public void setCancellationPolicy(String cancellationPolicy) { this.cancellationPolicy = cancellationPolicy; }

    // Helper methods
    public String getFormattedTotalPrice() {
        return String.format("$%.0f", totalPrice);
    }

    public String getStayDuration() {
        return numberOfNights + " night" + (numberOfNights > 1 ? "s" : "");
    }

    public String getGuestCount() {
        return numberOfGuests + " guest" + (numberOfGuests > 1 ? "s" : "");
    }
}
