package com.example.booking_agency.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.booking_agency.R;
import com.example.booking_agency.models.Service;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    public interface OnServiceClickListener {
        void onServiceClicked(Service service);
    }

    private List<Service> services;
    private OnServiceClickListener listener;

    public ServiceAdapter(List<Service> services, OnServiceClickListener listener) {
        this.services = services;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = services.get(position);
        holder.name.setText(service.getName());
        holder.provider.setText(service.getProviderName());
        holder.category.setText(service.getCategory());
        holder.price.setText("$" + String.format("%.2f", service.getPrice()));
        holder.rating.setText(String.format("%.1f", service.getRating()));
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onServiceClicked(service);
        });
    }

    @Override
    public int getItemCount() {
        return services != null ? services.size() : 0;
    }

    static class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView name, provider, category, price, rating;
        ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_service_name);
            provider = itemView.findViewById(R.id.item_service_provider);
            category = itemView.findViewById(R.id.item_service_category);
            price = itemView.findViewById(R.id.item_service_price);
            rating = itemView.findViewById(R.id.item_service_rating);
        }
    }
}
