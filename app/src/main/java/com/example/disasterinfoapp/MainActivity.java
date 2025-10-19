package com.example.disasterinfoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent; // IMPORTANT: Required for switching screens
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Declare private variables for all six buttons
    private Button button1, button2, button3, button4, button5, button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ensure this matches your XML file name
        setContentView(R.layout.activity_main);

        // 1. Initialize all six Button variables by finding their IDs from the XML
        button1 = findViewById(R.id.button1); // Earthquake
        button2 = findViewById(R.id.button2); // Flood
        button3 = findViewById(R.id.button3); // Fire
        button4 = findViewById(R.id.button4); // Typhoon
        button5 = findViewById(R.id.button5); // Emergency Hotlines
        button6 = findViewById(R.id.button6); // Evacuation Map

        // 2. Set the click listeners for all six buttons
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }

    /**
     * Handles clicks for all buttons on the dashboard and launches the appropriate activity.
     * Buttons 1-4 now go to dedicated pages. Buttons 5-6 use the generic 'safety_tips' page.
     * @param v The view (Button) that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = null;

        if (id == R.id.button1) {
            // Earthquake -> Dedicated page
            intent = new Intent(MainActivity.this, safety_tips.class);
        } else if (id == R.id.button2) {
            // Flood -> Dedicated page
            intent = new Intent(MainActivity.this, flood_tips.class);
        } else if (id == R.id.button3) {
            // Fire -> Dedicated page
            intent = new Intent(MainActivity.this, fire_tips.class);
        } else if (id == R.id.button4) {
            // Typhoon -> Dedicated page
            intent = new Intent(MainActivity.this, typhoon_tips.class);
        } else if (id == R.id.button5) {
            // Hotlines -> Generic safety_tips
            intent = new Intent(MainActivity.this, hotlines.class);
            intent.putExtra("DISASTER_TYPE", "Hotlines");
        } else if (id == R.id.button6) {
            // Evacuation Map -> Generic safety_tips
            intent = new Intent(MainActivity.this, map.class);
            intent.putExtra("DISASTER_TYPE", "EvacuationMap");
        } else {
            Toast.makeText(this, "Unknown button clicked.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Start the new activity (screen)
        startActivity(intent);
    }
}
