package com.example.pjt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    private DBconnexion db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Initialisation de la connexion à la base de données
        db = new DBconnexion(this);
    }

    public void onClickSuivant(View view) {
        // Récupérer les données saisies par l'utilisateur
        EditText editTextTitre = findViewById(R.id.editTextText);
        Spinner spinnerCategorie = findViewById(R.id.spinner1);
        EditText editTextSecteur = findViewById(R.id.edittext1);
        EditText editTextDescription = findViewById(R.id.editTextText5);
        Spinner spinnerVille = findViewById(R.id.spinner7);

        String titre = editTextTitre.getText().toString();
        String categorie = spinnerCategorie.getSelectedItem().toString();
        String secteur = editTextSecteur.getText().toString();
        String description = editTextDescription.getText().toString();
        String ville = spinnerVille.getSelectedItem().toString();

        // Vérifier si tous les champs sont remplis
        if (!titre.isEmpty() && !categorie.isEmpty() && !description.isEmpty() && !ville.isEmpty()) {
            // Enregistrer l'annonce dans la base de données
            long result = db.insertAnnonce(titre, categorie, secteur, description, ville);
            if (result != -1) {
                // L'enregistrement a réussi, passer à l'activité 4
                Intent intent = new Intent(this, MainActivity4.class);
                startActivity(intent);
            } else {
                // Une erreur s'est produite lors de l'enregistrement dans la base de données
                Toast.makeText(this, "Erreur lors de l'enregistrement de l'annonce", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Afficher un message si tous les champs ne sont pas remplis
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
        }
    }
}