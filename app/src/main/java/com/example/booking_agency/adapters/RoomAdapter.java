package com.example.booking_agency.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.booking_agency.R;
import com.example.booking_agency.models.Room;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    
    private Context context;
    private List<Room> roomList;
    private OnRoomClickListener onRoomClickListener;
    
    public interface OnRoomClickListener {
        void onRoomClick(Room room);
    }
    
    public RoomAdapter(Context context, List<Room> roomList) {
        this.context = context;
        this.roomList = roomList;
    }
    
    public void setOnRoomClickListener(OnRoomClickListener listener) {
        this.onRoomClickListener = listener;
    }
    
    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);
        
        holder.roomName.setText(room.getName());
        holder.hotelName.setText(room.getHotelName());
        holder.roomType.setText(room.getType());
        holder.price.setText(room.getFormattedPrice());
        holder.occupancy.setText(room.getOccupancyText());
        holder.rating.setText(String.format("%.1f", room.getRating()));
        holder.reviewCount.setText("(" + room.getReviewCount() + " reviews)");
        
        // Set room size if available
        if (room.getRoomSize() > 0) {
            holder.roomSize.setText(room.getRoomSizeText());
            holder.roomSize.setVisibility(View.VISIBLE);
        } else {
            holder.roomSize.setVisibility(View.GONE);
        }
        
        // Set bed type if available
        if (room.getBedType() != null && !room.getBedType().isEmpty()) {
            holder.bedType.setText(room.getBedType() + " bed");
            holder.bedType.setVisibility(View.VISIBLE);
        } else {
            holder.bedType.setVisibility(View.GONE);
        }
        
        // Set amenities preview
        if (room.getAmenities() != null && !room.getAmenities().isEmpty()) {
            StringBuilder amenitiesText = new StringBuilder();
            int maxAmenities = Math.min(3, room.getAmenities().size());
            for (int i = 0; i < maxAmenities; i++) {
                if (i > 0) amenitiesText.append(" • ");
                amenitiesText.append(room.getAmenities().get(i));
            }
            if (room.getAmenities().size() > 3) {
                amenitiesText.append(" +").append(room.getAmenities().size() - 3).append(" more");
            }
            holder.amenities.setText(amenitiesText.toString());
            holder.amenities.setVisibility(View.VISIBLE);
        } else {
            holder.amenities.setVisibility(View.GONE);
        }
        
        // Set availability status
        if (room.isAvailable()) {
            holder.availabilityStatus.setText("Available");
            holder.availabilityStatus.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_dark));
            holder.roomCard.setAlpha(1.0f);
        } else {
            holder.availabilityStatus.setText("Not Available");
            holder.availabilityStatus.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
            holder.roomCard.setAlpha(0.6f);
        }
        
        // Set click listener
        holder.roomCard.setOnClickListener(v -> {
            if (onRoomClickListener != null && room.isAvailable()) {
                onRoomClickListener.onRoomClick(room);
            }
        });
        
        // TODO: Load room image using image loading library like Glide or Picasso
        // For now, set a placeholder based on room type
        setRoomTypeImage(holder.roomImage, room.getType());
    }
    
    private void setRoomTypeImage(ImageView imageView, String roomType) {
        // Set placeholder images based on room type
        switch (roomType.toUpperCase()) {
            case "STANDARD":
                imageView.setImageResource(R.drawable.room_standard);
                break;
            case "DELUXE":
                imageView.setImageResource(R.drawable.room_deluxe);
                break;
            case "SUITE":
                imageView.setImageResource(R.drawable.room_suite);
                break;
            case "PRESIDENTIAL":
                imageView.setImageResource(R.drawable.room_presidential);
                break;
            default:
                imageView.setImageResource(R.drawable.room_standard);
                break;
        }
    }
    
    @Override
    public int getItemCount() {
        return roomList != null ? roomList.size() : 0;
    }
    
    public void updateRooms(List<Room> newRoomList) {
        this.roomList = newRoomList;
        notifyDataSetChanged();
    }
    
    static class RoomViewHolder extends RecyclerView.ViewHolder {
        CardView roomCard;
        ImageView roomImage;
        TextView roomName;
        TextView hotelName;
        TextView roomType;
        TextView price;
        TextView occupancy;
        TextView roomSize;
        TextView bedType;
        TextView amenities;
        TextView rating;
        TextView reviewCount;
        TextView availabilityStatus;
        
        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            roomCard = itemView.findViewById(R.id.room_card);
            roomImage = itemView.findViewById(R.id.room_image);
            roomName = itemView.findViewById(R.id.room_name);
            hotelName = itemView.findViewById(R.id.hotel_name);
            roomType = itemView.findViewById(R.id.room_type);
            price = itemView.findViewById(R.id.room_price);
            occupancy = itemView.findViewById(R.id.room_occupancy);
            roomSize = itemView.findViewById(R.id.room_size);
            bedType = itemView.findViewById(R.id.bed_type);
            amenities = itemView.findViewById(R.id.room_amenities);
            rating = itemView.findViewById(R.id.room_rating);
            reviewCount = itemView.findViewById(R.id.review_count);
            availabilityStatus = itemView.findViewById(R.id.availability_status);
        }
    }
}
