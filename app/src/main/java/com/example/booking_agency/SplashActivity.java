package com.example.booking_agency;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2500; // 2.5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initializeAnimations();
        navigateToMainActivity();
    }

    private void initializeAnimations() {
        // Find views
        ImageView logoImage = findViewById(R.id.splash_logo);
        TextView appName = findViewById(R.id.splash_app_name);
        TextView tagline = findViewById(R.id.splash_tagline);

        // Load animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        // Set animation durations
        fadeIn.setDuration(1000);
        slideUp.setDuration(800);
        slideUp.setStartOffset(300);

        // Apply animations
        if (logoImage != null) {
            logoImage.startAnimation(fadeIn);
        }
        if (appName != null) {
            appName.startAnimation(slideUp);
        }
        if (tagline != null) {
            tagline.startAnimation(slideUp);
            tagline.getAnimation().setStartOffset(500);
        }
    }

    private void navigateToMainActivity() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            
            // Add custom transition
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            
            // Close splash activity
            finish();
        }, SPLASH_DURATION);
    }

    @Override
    public void onBackPressed() {
        // Disable back button on splash screen
        // Do nothing
    }
}
