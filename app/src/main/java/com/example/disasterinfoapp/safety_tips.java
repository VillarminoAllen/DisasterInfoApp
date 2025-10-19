package com.example.disasterinfoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.disasterinfoapp.MainActivity;
import com.example.disasterinfoapp.R;

/**
 * Activity to display earthquake safety tips.
 * Includes a button to navigate back to the main dashboard (MainActivity).
 */
public class safety_tips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view using the XML layout
        setContentView(R.layout.activity_safety_tips);

        // 1. Find the "Back to Dashboard" button by its ID.
        Button backButton = findViewById(R.id.buttonBack);

        // 2. Set an OnClickListener to define the action when the button is tapped.
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to switch from the current activity
                // (SafetyTipsActivity) to the main activity (MainActivity).
                Intent intent = new Intent(safety_tips.this, MainActivity.class);

                // Start the new activity.
                startActivity(intent);

                // Finish the current activity so the user does not return to the safety tips
                // screen by pressing the hardware back button.
                finish();
            }
        });
    }
}
