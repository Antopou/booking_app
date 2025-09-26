package com.example.booking_agency.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.booking_agency.R;
import com.example.booking_agency.models.Booking;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private final List<Booking> bookings;

    public BookingAdapter(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking b = bookings.get(position);
        holder.serviceName.setText(b.getServiceName());
        holder.providerName.setText(b.getProviderName());
        holder.dateTime.setText(b.getBookingDate() + " • " + b.getBookingTime());
        holder.status.setText(b.getStatus());
        holder.price.setText("$" + String.format("%.2f", b.getTotalPrice()));
    }

    @Override
    public int getItemCount() {
        return bookings != null ? bookings.size() : 0;
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName, providerName, dateTime, status, price;
        BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.item_booking_service_name);
            providerName = itemView.findViewById(R.id.item_booking_provider_name);
            dateTime = itemView.findViewById(R.id.item_booking_datetime);
            status = itemView.findViewById(R.id.item_booking_status);
            price = itemView.findViewById(R.id.item_booking_price);
        }
    }
}
