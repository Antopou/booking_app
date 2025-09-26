package com.example.booking_agency;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.booking_agency.models.Service;
import com.example.booking_agency.utils.JsonDataManager;

public class ServiceDetailsActivity extends AppCompatActivity {

    private JsonDataManager dataManager;
    private String serviceId;
    private String userId;
    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);

        dataManager = new JsonDataManager(this);
        serviceId = getIntent().getStringExtra("serviceId");
        userId = getIntent().getStringExtra("userId");

        service = dataManager.getServiceById(serviceId);

        TextView name = findViewById(R.id.service_name);
        TextView provider = findViewById(R.id.service_provider);
        TextView description = findViewById(R.id.service_description);
        TextView price = findViewById(R.id.service_price);
        TextView duration = findViewById(R.id.service_duration);
        TextView rating = findViewById(R.id.service_rating);
        TextView location = findViewById(R.id.service_location);
        Button bookNow = findViewById(R.id.button_book_now);

        if (service != null) {
            name.setText(service.getName());
            provider.setText(service.getProviderName());
            description.setText(service.getDescription());
            price.setText("$" + String.format("%.2f", service.getPrice()));
            duration.setText(service.getDuration() + " mins");
            rating.setText(String.format("%.1f (%d Reviews)", service.getRating(), service.getReviewCount()));
            location.setText(service.getLocation());
        }

        bookNow.setOnClickListener(v -> openBookings());
    }

    private void openBookings() {
        Intent intent = new Intent(this, BookingsActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }
}
