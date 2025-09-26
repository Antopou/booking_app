package com.example.booking_agency.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.booking_agency.R;
import com.example.booking_agency.models.Booking;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private final List<Booking> bookings;
    private final Context context;
    private OnBookingActionListener actionListener;
    private int lastPosition = -1;

    public interface OnBookingActionListener {
        void onViewDetails(Booking booking);
        void onManageBooking(Booking booking);
        void onBookingClick(Booking booking);
    }

    public BookingAdapter(Context context, List<Booking> bookings) {
        this.context = context;
        this.bookings = bookings;
    }

    public void setOnBookingActionListener(OnBookingActionListener listener) {
        this.actionListener = listener;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookings.get(position);
        
        // Set basic information
        holder.serviceName.setText(booking.getRoomName());
        holder.providerName.setText(booking.getHotelName());
        
        // Format and set date range
        String dateRange = formatDateRange(booking.getCheckInDate(), booking.getCheckOutDate());
        holder.dateTime.setText(dateRange);
        
        // Set guest information
        int nights = calculateNights(booking.getCheckInDate(), booking.getCheckOutDate());
        String guestInfo = booking.getGuests() + " guests • " + nights + " nights";
        holder.guests.setText(guestInfo);
        
        // Set status with appropriate styling
        setStatusBadge(holder.status, booking.getStatus());
        
        // Set price
        holder.price.setText("$" + String.format("%.2f", booking.getTotalPrice()));
        
        // Set room image based on room type or use default
        setRoomImage(holder.roomImage, booking.getRoomType());
        
        // Set click listeners
        holder.itemView.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onBookingClick(booking);
            }
        });
        
        holder.viewDetailsBtn.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onViewDetails(booking);
            }
        });
        
        holder.manageBookingBtn.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onManageBooking(booking);
            }
        });
        
        // Add animation for newly visible items
        setAnimation(holder.itemView, position);
    }

    private String formatDateRange(String checkInDate, String checkOutDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd", Locale.getDefault());
            
            Date checkIn = inputFormat.parse(checkInDate);
            Date checkOut = inputFormat.parse(checkOutDate);
            
            if (checkIn != null && checkOut != null) {
                return outputFormat.format(checkIn) + " - " + outputFormat.format(checkOut) + ", 2024";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkInDate + " - " + checkOutDate;
    }

    private int calculateNights(String checkInDate, String checkOutDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date checkIn = format.parse(checkInDate);
            Date checkOut = format.parse(checkOutDate);
            
            if (checkIn != null && checkOut != null) {
                long diffInMillies = checkOut.getTime() - checkIn.getTime();
                return (int) (diffInMillies / (1000 * 60 * 60 * 24));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1; // Default to 1 night
    }

    private void setStatusBadge(TextView statusView, String status) {
        statusView.setText(status.toUpperCase());
        
        int backgroundRes;
        int textColor = ContextCompat.getColor(context, R.color.text_on_primary);
        
        switch (status.toLowerCase()) {
            case "confirmed":
                backgroundRes = R.drawable.status_confirmed_background;
                break;
            case "pending":
                backgroundRes = R.drawable.status_pending_background;
                break;
            case "cancelled":
                backgroundRes = R.drawable.status_cancelled_background;
                break;
            default:
                backgroundRes = R.drawable.status_confirmed_background;
                break;
        }
        
        statusView.setBackground(ContextCompat.getDrawable(context, backgroundRes));
        statusView.setTextColor(textColor);
    }

    private void setRoomImage(ImageView imageView, String roomType) {
        int imageRes;
        switch (roomType.toLowerCase()) {
            case "standard":
                imageRes = R.drawable.room_standard;
                break;
            case "deluxe":
                imageRes = R.drawable.room_deluxe;
                break;
            case "suite":
                imageRes = R.drawable.room_suite;
                break;
            case "presidential":
                imageRes = R.drawable.room_presidential;
                break;
            default:
                imageRes = R.drawable.room_standard;
                break;
        }
        imageView.setImageResource(imageRes);
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            animation.setDuration(300);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BookingViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return bookings != null ? bookings.size() : 0;
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {
        ImageView roomImage;
        TextView serviceName, providerName, dateTime, guests, status, price;
        Button viewDetailsBtn, manageBookingBtn;
        
        BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            roomImage = itemView.findViewById(R.id.booking_room_image);
            serviceName = itemView.findViewById(R.id.item_booking_service_name);
            providerName = itemView.findViewById(R.id.item_booking_provider_name);
            dateTime = itemView.findViewById(R.id.item_booking_datetime);
            guests = itemView.findViewById(R.id.item_booking_guests);
            status = itemView.findViewById(R.id.item_booking_status);
            price = itemView.findViewById(R.id.item_booking_price);
            viewDetailsBtn = itemView.findViewById(R.id.btn_view_details);
            manageBookingBtn = itemView.findViewById(R.id.btn_manage_booking);
        }
    }
}
