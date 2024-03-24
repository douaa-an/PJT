package com.example.pjt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DBconnexion dbHelper;
    private EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBconnexion(this);
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);

        Button btnConnexion = findViewById(R.id.button);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seConnecter();
            }
        });
    }

    private void seConnecter() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {
            if (dbHelper.checkUser(email, password)) {
                // Les données de connexion sont valides
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            } else {
                // Les données de connexion ne correspondent pas
                Toast.makeText(this, "Identifiants incorrects", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
        }

    }
    public void insClick(View view) {
        // Créez un Intent pour démarrer MainActivity2
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
}
}
