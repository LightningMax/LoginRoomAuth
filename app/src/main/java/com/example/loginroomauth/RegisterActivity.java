package com.example.loginroomauth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText nameInput, emailInput, passwordInput, confirmpasswordInput;
    Button registerBtn;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailInput = findViewById(R.id.mail_input_register);
        passwordInput = findViewById(R.id.password_input_register);
        registerBtn = findViewById(R.id.button_register);
        nameInput = findViewById(R.id.nom_input_register);
        confirmpasswordInput = findViewById(R.id.confirm_password_input_register);

        sharedPreferences = getSharedPreferences("LoginRoomAuth", Context.MODE_PRIVATE);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();
                String nom = nameInput.getText().toString().trim();
                String confirmpass = confirmpasswordInput.getText().toString().trim();

                User user = new User(nom, email, password);
                AppDatabase db = AppDatabase.getInstance(RegisterActivity.this);
                db.userDAO().inserer(user);

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!confirmpass.equals(password)){
                    Toast.makeText(RegisterActivity.this, "Confirmation incorrecte", Toast.LENGTH_SHORT).show();
                    return;
                }

                saveUser(email, password, nom);
                Toast.makeText(RegisterActivity.this, "Compte créé avec succès !", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void saveUser(String EMAIL, String PASSWORD, String NOM) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_nom", NOM);
        editor.putString("user_email", EMAIL);
        editor.putString("user_password", PASSWORD);
        editor.apply();
    }
}