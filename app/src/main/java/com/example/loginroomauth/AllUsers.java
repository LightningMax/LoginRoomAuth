package com.example.loginroomauth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AllUsers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        ListView listView = findViewById(R.id.listViewUsers);
        Button btnHome = findViewById(R.id.button);

        // Get database
        AppDatabase db = AppDatabase.getInstance(this);

        // Go back to Home when button clicked
        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(AllUsers.this, HomeActivity.class));
            finish();
        });

        // Run database call in background thread
        new Thread(() -> {

            List<User> userList = db.userDAO().listUser();

            List<String> displayList = new ArrayList<>();
            for (User user : userList) {
                displayList.add(user.name + " - " + user.email);
            }

            // Come back to main thread to update the screen
            runOnUiThread(() -> {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        displayList
                );
                listView.setAdapter(adapter);
            });

        }).start();
    }
}