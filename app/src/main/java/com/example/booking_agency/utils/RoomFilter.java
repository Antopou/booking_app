package com.example.booking_agency.utils;

import com.example.booking_agency.models.Room;
import java.util.ArrayList;
import java.util.List;

public class RoomFilter {
    
    public static class FilterCriteria {
        private String roomType;
        private double minPrice;
        private double maxPrice;
        private int minOccupancy;
        private boolean hasWifi;
        private boolean hasAirConditioning;
        private boolean hasBalcony;
        private boolean hasSeaView;
        private boolean hasCityView;
        private boolean hasBreakfast;
        private double minRating;
        private String bedType;
        
        public FilterCriteria() {
            this.minPrice = 0;
            this.maxPrice = Double.MAX_VALUE;
            this.minOccupancy = 1;
            this.minRating = 0.0;
        }
        
        // Getters and Setters
        public String getRoomType() { return roomType; }
        public void setRoomType(String roomType) { this.roomType = roomType; }
        
        public double getMinPrice() { return minPrice; }
        public void setMinPrice(double minPrice) { this.minPrice = minPrice; }
        
        public double getMaxPrice() { return maxPrice; }
        public void setMaxPrice(double maxPrice) { this.maxPrice = maxPrice; }
        
        public int getMinOccupancy() { return minOccupancy; }
        public void setMinOccupancy(int minOccupancy) { this.minOccupancy = minOccupancy; }
        
        public boolean isHasWifi() { return hasWifi; }
        public void setHasWifi(boolean hasWifi) { this.hasWifi = hasWifi; }
        
        public boolean isHasAirConditioning() { return hasAirConditioning; }
        public void setHasAirConditioning(boolean hasAirConditioning) { this.hasAirConditioning = hasAirConditioning; }
        
        public boolean isHasBalcony() { return hasBalcony; }
        public void setHasBalcony(boolean hasBalcony) { this.hasBalcony = hasBalcony; }
        
        public boolean isHasSeaView() { return hasSeaView; }
        public void setHasSeaView(boolean hasSeaView) { this.hasSeaView = hasSeaView; }
        
        public boolean isHasCityView() { return hasCityView; }
        public void setHasCityView(boolean hasCityView) { this.hasCityView = hasCityView; }
        
        public boolean isHasBreakfast() { return hasBreakfast; }
        public void setHasBreakfast(boolean hasBreakfast) { this.hasBreakfast = hasBreakfast; }
        
        public double getMinRating() { return minRating; }
        public void setMinRating(double minRating) { this.minRating = minRating; }
        
        public String getBedType() { return bedType; }
        public void setBedType(String bedType) { this.bedType = bedType; }
    }
    
    public static List<Room> filterRooms(List<Room> rooms, FilterCriteria criteria) {
        List<Room> filteredRooms = new ArrayList<>();
        
        for (Room room : rooms) {
            if (matchesCriteria(room, criteria)) {
                filteredRooms.add(room);
            }
        }
        
        return filteredRooms;
    }
    
    private static boolean matchesCriteria(Room room, FilterCriteria criteria) {
        // Check room type
        if (criteria.getRoomType() != null && !criteria.getRoomType().isEmpty()) {
            if (!room.getType().equalsIgnoreCase(criteria.getRoomType())) {
                return false;
            }
        }
        
        // Check price range
        if (room.getPricePerNight() < criteria.getMinPrice() || 
            room.getPricePerNight() > criteria.getMaxPrice()) {
            return false;
        }
        
        // Check occupancy
        if (room.getMaxOccupancy() < criteria.getMinOccupancy()) {
            return false;
        }
        
        // Check rating
        if (room.getRating() < criteria.getMinRating()) {
            return false;
        }
        
        // Check bed type
        if (criteria.getBedType() != null && !criteria.getBedType().isEmpty()) {
            if (room.getBedType() == null || 
                !room.getBedType().equalsIgnoreCase(criteria.getBedType())) {
                return false;
            }
        }
        
        // Check amenities
        if (criteria.isHasWifi() && !room.isHasWifi()) {
            return false;
        }
        
        if (criteria.isHasAirConditioning() && !room.isHasAirConditioning()) {
            return false;
        }
        
        if (criteria.isHasBalcony() && !room.isHasBalcony()) {
            return false;
        }
        
        if (criteria.isHasSeaView() && !room.isHasSeaView()) {
            return false;
        }
        
        if (criteria.isHasCityView() && !room.isHasCityView()) {
            return false;
        }
        
        if (criteria.isHasBreakfast() && !room.isHasBreakfast()) {
            return false;
        }
        
        return true;
    }
    
    public static List<Room> sortRoomsByPrice(List<Room> rooms, boolean ascending) {
        List<Room> sortedRooms = new ArrayList<>(rooms);
        sortedRooms.sort((r1, r2) -> {
            if (ascending) {
                return Double.compare(r1.getPricePerNight(), r2.getPricePerNight());
            } else {
                return Double.compare(r2.getPricePerNight(), r1.getPricePerNight());
            }
        });
        return sortedRooms;
    }
    
    public static List<Room> sortRoomsByRating(List<Room> rooms, boolean ascending) {
        List<Room> sortedRooms = new ArrayList<>(rooms);
        sortedRooms.sort((r1, r2) -> {
            if (ascending) {
                return Double.compare(r1.getRating(), r2.getRating());
            } else {
                return Double.compare(r2.getRating(), r1.getRating());
            }
        });
        return sortedRooms;
    }
    
    public static List<Room> getAvailableRooms(List<Room> rooms) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
}
