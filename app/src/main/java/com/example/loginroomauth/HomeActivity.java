package com.example.loginroomauth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    // private AppDatabase db;
    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE); // "LoginRoomAuth", Context.MODE_PRIVATE
        String user = prefs.getString("User_name","anonyme");
        String email = prefs.getString("User_email","Email_anonyme");



        TextView nomView = findViewById(R.id.name_view);
        TextView emailView = findViewById(R.id.email_view);
        Button btnProfile = findViewById(R.id.btn_profile);
        Button btnLogout = findViewById(R.id.btn_logout);

        nomView.setText("Bienvenu " + user);
        emailView.setText(email);

        btnProfile.setOnClickListener(v -> {
            if (emailView.getVisibility() == View.VISIBLE) {
                emailView.setVisibility(View.INVISIBLE);
            } else {
                emailView.setVisibility(View.VISIBLE);
            }
        });

        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();

            editor.clear();
            editor.apply();

            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });



    }
}