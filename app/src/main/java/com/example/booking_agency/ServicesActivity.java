package com.example.booking_agency;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.booking_agency.adapters.ServiceAdapter;
import com.example.booking_agency.models.Service;
import com.example.booking_agency.utils.JsonDataManager;
import java.util.ArrayList;
import java.util.List;

public class ServicesActivity extends AppCompatActivity implements ServiceAdapter.OnServiceClickListener {

    private RecyclerView recyclerView;
    private ServiceAdapter adapter;
    private JsonDataManager dataManager;
    private String category;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        dataManager = new JsonDataManager(this);
        category = getIntent().getStringExtra("category");
        userId = getIntent().getStringExtra("userId");

        TextView title = findViewById(R.id.services_title);
        if (title != null) {
            title.setText(category != null && !category.equals("All") ? category + " Services" : "All Services");
        }

        recyclerView = findViewById(R.id.services_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Service> services = new ArrayList<>();
        if (category == null || category.equals("All")) {
            services = dataManager.getServices();
        } else {
            services = dataManager.getServicesByCategory(category);
        }

        adapter = new ServiceAdapter(services, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onServiceClicked(Service service) {
        Intent intent = new Intent(this, ServiceDetailsActivity.class);
        intent.putExtra("serviceId", service.getId());
        intent.putExtra("userId", userId);
        startActivity(intent);
    }
}
