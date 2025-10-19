package com.example.disasterinfoapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class hotlines extends AppCompatActivity {

    // Request code for the CALL_PHONE permission
    private static final int PERMISSION_CALL_PHONE = 1;
    // Variable to temporarily hold the number before the call is initiated
    private String numberToCall = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view using the XML layout
        setContentView(R.layout.activity_hotlines);

        // 1. Find the "Back to Dashboard" button by its ID.
        Button backButton = findViewById(R.id.buttonBack);

        // 2. Set an OnClickListener to define the action when the button is tapped.
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity
                Intent intent = new Intent(hotlines.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Note: No need to find the TextViews here, as their click handling is
        // defined directly in the XML using android:onClick="callHotline"
    }

    // =====================================================================
    //                  PHONE CALL FUNCTIONALITY
    // =====================================================================

    /**
     * This method is called from the XML via android:onClick="callHotline".
     * It handles the click on a phone number TextView.
     * @param view The TextView that was clicked (with the phone number).
     */
    public void callHotline(View view) {
        TextView phoneNumberView = (TextView) view;

        // 1. Extract and clean the phone number (removes hyphens and spaces)
        numberToCall = phoneNumberView.getText().toString().trim().replaceAll("[\\s-]+", "");

        // 2. Check for the CALL_PHONE permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            // Permission is NOT granted, request it from the user at runtime
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    PERMISSION_CALL_PHONE
            );

        } else {
            // Permission IS granted, proceed to call immediately
            initiateCall();
        }
    }

    /**
     * Handles the result returned after the user responds to the permission request dialog.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_CALL_PHONE) {
            // Check if the permission request was granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, now initiate the call
                initiateCall();
            } else {
                // Permission denied
                Toast.makeText(this, "Call permission denied. Cannot make calls directly.", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Creates and starts the Intent to place the phone call using the stored number.
     */
    private void initiateCall() {
        if (!numberToCall.isEmpty()) {
            try {
                // ACTION_CALL directly initiates the call (requires CALL_PHONE permission)
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + numberToCall));
                startActivity(intent);
            } catch (SecurityException e) {
                Toast.makeText(this, "Error: Unable to place call. Check app permissions.", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}