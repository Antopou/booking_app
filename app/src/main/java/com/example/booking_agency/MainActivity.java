package com.example.booking_agency;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.booking_agency.models.User;
import com.example.booking_agency.utils.JsonDataManager;

public class MainActivity extends AppCompatActivity {
    
    private JsonDataManager dataManager;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Hide the ActionBar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        dataManager = new JsonDataManager(this);

        // For demo purposes, use the first user as current user
        currentUser = dataManager.getUsers().get(0);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        // Update user name in the greeting
        TextView greetingName = findViewById(R.id.greeting_name);
        if (greetingName != null && currentUser != null) {
            greetingName.setText("Hi " + currentUser.getName().split(" ")[0]);
        }
    }
    
    private void setupClickListeners() {
        // Set up click listeners for new categories by ID
        LinearLayout catSpa = findViewById(R.id.cat_spa);
        LinearLayout catHotel = findViewById(R.id.cat_hotel);
        LinearLayout catEvent = findViewById(R.id.cat_event);
        LinearLayout catFood = findViewById(R.id.cat_food);

        if (catSpa != null) {
            catSpa.setOnClickListener(v -> openServicesActivity("Spa"));
        }
        if (catHotel != null) {
            catHotel.setOnClickListener(v -> openServicesActivity("Hotel"));
        }
        if (catEvent != null) {
            catEvent.setOnClickListener(v -> openServicesActivity("Event"));
        }
        if (catFood != null) {
            catFood.setOnClickListener(v -> openServicesActivity("Food"));
        }

        // Set up click listeners for featured service cards by ID
        CardView featuredSpa = findViewById(R.id.featured_spa_card);
        CardView featuredHotel = findViewById(R.id.featured_hotel_card);

        if (featuredSpa != null) {
            featuredSpa.setOnClickListener(v -> openServiceDetails("featured_spa"));
        }
        if (featuredHotel != null) {
            featuredHotel.setOnClickListener(v -> openServiceDetails("featured_hotel"));
        }

        // Set up click listener for booking summary card's button
        View viewDetailsBtn = findViewById(R.id.btn_view_details);
        if (viewDetailsBtn != null) {
            viewDetailsBtn.setOnClickListener(v -> openBookingsActivity());
        }
    }
    
    private void openServicesActivity(String category) {
        Intent intent = new Intent(this, ServicesActivity.class);
        intent.putExtra("category", category);
        intent.putExtra("userId", currentUser.getId());
        startActivity(intent);
    }
    
    private void openServiceDetails(String serviceId) {
        Intent intent = new Intent(this, ServiceDetailsActivity.class);
        intent.putExtra("serviceId", serviceId);
        intent.putExtra("userId", currentUser.getId());
        startActivity(intent);
    }
    
    private void openBookingsActivity() {
        Intent intent = new Intent(this, BookingsActivity.class);
        intent.putExtra("userId", currentUser.getId());
        startActivity(intent);
    }
}
