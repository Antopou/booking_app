package com.example.booking_agency.utils;

import android.content.Context;
import com.example.booking_agency.models.User;
import com.example.booking_agency.models.Service;
import com.example.booking_agency.models.Booking;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonDataManager {
    private Context context;

    public JsonDataManager(Context context) {
        this.context = context;
    }

    private String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("data/" + fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            String jsonString = loadJSONFromAsset("users.json");
            if (jsonString != null) {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray usersArray = jsonObject.getJSONArray("users");
                
                for (int i = 0; i < usersArray.length(); i++) {
                    JSONObject userJson = usersArray.getJSONObject(i);
                    User user = new User();
                    user.setId(userJson.getString("id"));
                    user.setName(userJson.getString("name"));
                    user.setEmail(userJson.getString("email"));
                    user.setPhone(userJson.getString("phone"));
                    user.setProfileImage(userJson.optString("profileImage"));
                    user.setAddress(userJson.optString("address"));
                    user.setDateOfBirth(userJson.optString("dateOfBirth"));
                    user.setGender(userJson.optString("gender"));
                    users.add(user);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Service> getServices() {
        List<Service> services = new ArrayList<>();
        try {
            String jsonString = loadJSONFromAsset("services.json");
            if (jsonString != null) {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray servicesArray = jsonObject.getJSONArray("services");
                
                for (int i = 0; i < servicesArray.length(); i++) {
                    JSONObject serviceJson = servicesArray.getJSONObject(i);
                    Service service = new Service();
                    service.setId(serviceJson.getString("id"));
                    service.setName(serviceJson.getString("name"));
                    service.setDescription(serviceJson.getString("description"));
                    service.setCategory(serviceJson.getString("category"));
                    service.setPrice(serviceJson.getDouble("price"));
                    service.setDuration(serviceJson.getInt("duration"));
                    service.setProviderId(serviceJson.optString("providerId"));
                    service.setProviderName(serviceJson.optString("providerName"));
                    service.setProviderImage(serviceJson.optString("providerImage"));
                    service.setRating(serviceJson.optDouble("rating", 0.0));
                    service.setReviewCount(serviceJson.optInt("reviewCount", 0));
                    service.setLocation(serviceJson.optString("location"));
                    service.setDistance(serviceJson.optDouble("distance", 0.0));
                    service.setAvailable(serviceJson.optBoolean("isAvailable", true));
                    
                    // Handle time slots array
                    JSONArray timeSlotsArray = serviceJson.optJSONArray("availableTimeSlots");
                    if (timeSlotsArray != null) {
                        List<String> timeSlots = new ArrayList<>();
                        for (int j = 0; j < timeSlotsArray.length(); j++) {
                            timeSlots.add(timeSlotsArray.getString(j));
                        }
                        service.setAvailableTimeSlots(timeSlots);
                    }
                    
                    // Handle images array
                    JSONArray imagesArray = serviceJson.optJSONArray("images");
                    if (imagesArray != null) {
                        List<String> images = new ArrayList<>();
                        for (int j = 0; j < imagesArray.length(); j++) {
                            images.add(imagesArray.getString(j));
                        }
                        service.setImages(images);
                    }
                    
                    services.add(service);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return services;
    }

    public List<Booking> getBookings() {
        List<Booking> bookings = new ArrayList<>();
        try {
            String jsonString = loadJSONFromAsset("bookings.json");
            if (jsonString != null) {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray bookingsArray = jsonObject.getJSONArray("bookings");
                
                for (int i = 0; i < bookingsArray.length(); i++) {
                    JSONObject bookingJson = bookingsArray.getJSONObject(i);
                    Booking booking = new Booking();
                    booking.setId(bookingJson.getString("id"));
                    booking.setUserId(bookingJson.getString("userId"));
                    booking.setServiceId(bookingJson.getString("serviceId"));
                    booking.setServiceName(bookingJson.getString("serviceName"));
                    booking.setProviderName(bookingJson.optString("providerName"));
                    booking.setProviderImage(bookingJson.optString("providerImage"));
                    booking.setBookingDate(bookingJson.getString("bookingDate"));
                    booking.setBookingTime(bookingJson.getString("bookingTime"));
                    booking.setTotalPrice(bookingJson.getDouble("totalPrice"));
                    booking.setStatus(bookingJson.getString("status"));
                    booking.setNotes(bookingJson.optString("notes"));
                    booking.setCreatedAt(bookingJson.optString("createdAt"));
                    booking.setUpdatedAt(bookingJson.optString("updatedAt"));
                    booking.setLocation(bookingJson.optString("location"));
                    booking.setDuration(bookingJson.optInt("duration", 0));
                    bookings.add(booking);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public User getUserById(String userId) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public Service getServiceById(String serviceId) {
        List<Service> services = getServices();
        for (Service service : services) {
            if (service.getId().equals(serviceId)) {
                return service;
            }
        }
        return null;
    }

    public List<Service> getServicesByCategory(String category) {
        List<Service> allServices = getServices();
        List<Service> filteredServices = new ArrayList<>();
        for (Service service : allServices) {
            if (service.getCategory().equalsIgnoreCase(category)) {
                filteredServices.add(service);
            }
        }
        return filteredServices;
    }

    public List<Booking> getBookingsByUserId(String userId) {
        List<Booking> allBookings = getBookings();
        List<Booking> userBookings = new ArrayList<>();
        for (Booking booking : allBookings) {
            if (booking.getUserId().equals(userId)) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }

    public List<String> getServiceCategories() {
        return Arrays.asList("Professional", "Wellness", "Health", "Business");
    }
}
