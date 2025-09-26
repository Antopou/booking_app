package com.example.booking_agency;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.booking_agency.adapters.BookingAdapter;
import com.example.booking_agency.models.Booking;
import com.example.booking_agency.utils.JsonDataManager;
import java.util.List;

public class BookingsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookingAdapter adapter;
    private JsonDataManager dataManager;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        userId = getIntent().getStringExtra("userId");
        dataManager = new JsonDataManager(this);

        TextView title = findViewById(R.id.bookings_title);
        if (title != null) title.setText("My Bookings");

        recyclerView = findViewById(R.id.bookings_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Booking> bookings = dataManager.getBookingsByUserId(userId);
        adapter = new BookingAdapter(bookings);
        recyclerView.setAdapter(adapter);
    }
}
