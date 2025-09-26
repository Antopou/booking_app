package com.example.booking_agency;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.booking_agency.adapters.BookingAdapter;
import com.example.booking_agency.models.Booking;
import com.example.booking_agency.utils.JsonDataManager;
import java.util.List;

public class BookingsActivity extends AppCompatActivity implements BookingAdapter.OnBookingActionListener {

    private RecyclerView recyclerView;
    private BookingAdapter adapter;
    private JsonDataManager dataManager;
    private ProgressBar progressBar;
    private TextView emptyStateText;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setTitle("My Bookings");
            }
        }

        // Handle back button
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        userId = getIntent().getStringExtra("userId");
        dataManager = new JsonDataManager(this);

        initializeViews();
        setupRecyclerView();
        loadBookings();
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.bookings_recycler);
        progressBar = findViewById(R.id.progress_bar);
        emptyStateText = findViewById(R.id.empty_state_text);
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        
        // Add item decoration for better spacing
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(android.graphics.Rect outRect, View view, 
                                     RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 8;
                outRect.top = 8;
            }
        });
    }

    private void loadBookings() {
        showLoading(true);
        
        // Simulate loading delay for better UX
        recyclerView.postDelayed(() -> {
            try {
                List<Booking> bookings = dataManager.getBookingsByUserId(userId);
                
                if (bookings != null && !bookings.isEmpty()) {
                    adapter = new BookingAdapter(this, bookings);
                    adapter.setOnBookingActionListener(this);
                    recyclerView.setAdapter(adapter);
                    showEmptyState(false);
                } else {
                    showEmptyState(true);
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error loading bookings: " + e.getMessage(), 
                             Toast.LENGTH_SHORT).show();
                showEmptyState(true);
            } finally {
                showLoading(false);
            }
        }, 500);
    }

    private void showLoading(boolean show) {
        if (progressBar != null) {
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
        recyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    private void showEmptyState(boolean show) {
        if (emptyStateText != null) {
            emptyStateText.setVisibility(show ? View.VISIBLE : View.GONE);
            if (show) {
                emptyStateText.setText("No bookings found.\nStart exploring rooms to make your first reservation!");
            }
        }
        recyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    // BookingAdapter.OnBookingActionListener implementation
    @Override
    public void onViewDetails(Booking booking) {
        Intent intent = new Intent(this, ServiceDetailsActivity.class);
        intent.putExtra("roomId", booking.getRoomId());
        intent.putExtra("bookingId", booking.getId());
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    @Override
    public void onManageBooking(Booking booking) {
        // Create a simple dialog or navigate to booking management
        Toast.makeText(this, "Managing booking for " + booking.getRoomName(), 
                     Toast.LENGTH_SHORT).show();
        
        // TODO: Implement booking management functionality
        // This could include options like:
        // - Modify dates
        // - Cancel booking
        // - Add special requests
        // - Contact hotel
    }

    @Override
    public void onBookingClick(Booking booking) {
        // Same as view details for now
        onViewDetails(booking);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh bookings when returning to this activity
        if (adapter != null) {
            loadBookings();
        }
    }
}
