package com.example.pjt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private DBconnexion dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dbHelper = new DBconnexion(this);
    }

    public void clickme(View view){
        EditText txtemail = findViewById(R.id.editTextText4);
        EditText txtmotpasse = findViewById(R.id.editTextText6);
        EditText txtconf = findViewById(R.id.editTextText8);
        String email = txtemail.getText().toString();
        String motDePasse = txtmotpasse.getText().toString();
        String motDePasseconf = txtconf.getText().toString();

        if (!email.isEmpty() && !motDePasse.isEmpty() && !motDePasseconf.isEmpty() ){
            // Vérifiez si les mots de passe correspondent
            if (motDePasse.equals(motDePasseconf)) {
                // Insertion des données dans la base de données
                long id = dbHelper.insertUser(email, motDePasse);
                if (id != -1) {
                    Toast.makeText(this, "Inscription avec succès", Toast.LENGTH_SHORT).show();
                    Intent myintent2 = new Intent(this, MainActivity.class);
                    startActivity(myintent2);
                } else {
                    Toast.makeText(this, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
        }
    }
}