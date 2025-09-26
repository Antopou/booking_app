package com.example.booking_agency.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.booking_agency.R;
import com.example.booking_agency.models.Room;
import com.example.booking_agency.models.Booking;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BookingConfirmationDialog extends Dialog {
    
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private int numberOfGuests;
    private int numberOfNights;
    private OnBookingConfirmedListener listener;
    
    public interface OnBookingConfirmedListener {
        void onBookingConfirmed(Booking booking);
    }
    
    public BookingConfirmationDialog(@NonNull Context context, Room room, 
                                   String checkInDate, String checkOutDate, 
                                   int numberOfGuests, int numberOfNights) {
        super(context);
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.numberOfNights = numberOfNights;
    }
    
    public void setOnBookingConfirmedListener(OnBookingConfirmedListener listener) {
        this.listener = listener;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_booking_confirmation);
        
        setupViews();
        setupClickListeners();
    }
    
    private void setupViews() {
        TextView roomNameText = findViewById(R.id.dialog_room_name);
        TextView hotelNameText = findViewById(R.id.dialog_hotel_name);
        TextView checkInText = findViewById(R.id.dialog_check_in);
        TextView checkOutText = findViewById(R.id.dialog_check_out);
        TextView guestsText = findViewById(R.id.dialog_guests);
        TextView nightsText = findViewById(R.id.dialog_nights);
        TextView pricePerNightText = findViewById(R.id.dialog_price_per_night);
        TextView totalPriceText = findViewById(R.id.dialog_total_price);
        TextView cancellationPolicyText = findViewById(R.id.dialog_cancellation_policy);
        
        if (room != null) {
            roomNameText.setText(room.getName());
            hotelNameText.setText(room.getHotelName());
            pricePerNightText.setText(room.getFormattedPrice());
            
            double totalPrice = room.getPricePerNight() * numberOfNights;
            totalPriceText.setText(String.format(Locale.getDefault(), "$%.0f", totalPrice));
            
            if (room.getCancellationPolicy() != null && !room.getCancellationPolicy().isEmpty()) {
                cancellationPolicyText.setText(room.getCancellationPolicy());
            } else {
                cancellationPolicyText.setText("Standard cancellation policy applies");
            }
        }
        
        checkInText.setText(checkInDate + " (" + (room != null ? room.getCheckInTime() : "14:00") + ")");
        checkOutText.setText(checkOutDate + " (" + (room != null ? room.getCheckOutTime() : "11:00") + ")");
        guestsText.setText(String.valueOf(numberOfGuests));
        nightsText.setText(String.valueOf(numberOfNights));
    }
    
    private void setupClickListeners() {
        Button confirmButton = findViewById(R.id.btn_confirm_booking);
        Button cancelButton = findViewById(R.id.btn_cancel_booking);
        
        confirmButton.setOnClickListener(v -> {
            if (listener != null && room != null) {
                Booking booking = createBooking();
                listener.onBookingConfirmed(booking);
            }
            dismiss();
        });
        
        cancelButton.setOnClickListener(v -> dismiss());
    }
    
    private Booking createBooking() {
        String bookingId = "booking_" + System.currentTimeMillis();
        String userId = "user_001"; // This should come from the current user session
        
        Booking booking = new Booking(bookingId, userId, room.getId(), room.getName(),
                                    checkInDate, checkOutDate, numberOfNights, room.getPricePerNight());
        
        booking.setHotelName(room.getHotelName());
        booking.setHotelImage(room.getHotelImage());
        booking.setCheckInTime(room.getCheckInTime());
        booking.setCheckOutTime(room.getCheckOutTime());
        booking.setNumberOfGuests(numberOfGuests);
        booking.setLocation(room.getLocation());
        booking.setAddress(room.getAddress());
        booking.setRoomType(room.getType());
        booking.setBedType(room.getBedType());
        booking.setHasBreakfast(room.isHasBreakfast());
        booking.setCancellationPolicy(room.getCancellationPolicy());
        
        // Set current timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        booking.setCreatedAt(currentTime);
        booking.setUpdatedAt(currentTime);
        
        return booking;
    }
}
