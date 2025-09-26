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
        // Set up click listeners for room categories by ID
        LinearLayout catStandard = findViewById(R.id.cat_standard);
        LinearLayout catDeluxe = findViewById(R.id.cat_deluxe);
        LinearLayout catSuite = findViewById(R.id.cat_suite);
        LinearLayout catPresidential = findViewById(R.id.cat_presidential);

        if (catStandard != null) {
            catStandard.setOnClickListener(v -> openRoomsActivity("STANDARD"));
        }
        if (catDeluxe != null) {
            catDeluxe.setOnClickListener(v -> openRoomsActivity("DELUXE"));
        }
        if (catSuite != null) {
            catSuite.setOnClickListener(v -> openRoomsActivity("SUITE"));
        }
        if (catPresidential != null) {
            catPresidential.setOnClickListener(v -> openRoomsActivity("PRESIDENTIAL"));
        }

        // Set up click listeners for featured room cards by ID
        CardView featuredDeluxe = findViewById(R.id.featured_deluxe_card);
        CardView featuredSuite = findViewById(R.id.featured_suite_card);

        if (featuredDeluxe != null) {
            featuredDeluxe.setOnClickListener(v -> openRoomDetails("deluxe_ocean_view"));
        }
        if (featuredSuite != null) {
            featuredSuite.setOnClickListener(v -> openRoomDetails("executive_suite"));
        }

        // Set up click listener for booking summary card's button
        View viewDetailsBtn = findViewById(R.id.btn_view_details);
        if (viewDetailsBtn != null) {
            viewDetailsBtn.setOnClickListener(v -> openBookingsActivity());
        }
    }
    
    private void openRoomsActivity(String roomType) {
        Intent intent = new Intent(this, ServicesActivity.class); // Will be renamed to RoomsActivity later
        intent.putExtra("roomType", roomType);
        intent.putExtra("userId", currentUser.getId());
        startActivity(intent);
    }
    
    private void openRoomDetails(String roomId) {
        Intent intent = new Intent(this, ServiceDetailsActivity.class); // Will be renamed to RoomDetailsActivity later
        intent.putExtra("roomId", roomId);
        intent.putExtra("userId", currentUser.getId());
        startActivity(intent);
    }
    
    private void openBookingsActivity() {
        Intent intent = new Intent(this, BookingsActivity.class);
        intent.putExtra("userId", currentUser.getId());
        startActivity(intent);
    }
}
