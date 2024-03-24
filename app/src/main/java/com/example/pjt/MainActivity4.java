package com.example.pjt;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {

    private DBconnexion db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // Initialisation de la connexion à la base de données
        db = new DBconnexion(this);

        // Appel de la méthode pour récupérer le nombre d'annonces par ville
        afficherNombreAnnoncesParVille();
    }

    private void afficherNombreAnnoncesParVille() {
        // Récupération du nombre d'annonces par ville depuis la base de données
        Cursor cursor = db.getNombreAnnoncesParVille();

        // Vérification si le curseur est valide
        if (cursor != null) {
            try {
                // Récupération des données et affichage dans un TextView
                TextView textViewNombreAnnonces = findViewById(R.id.textView25);
                StringBuilder stringBuilder = new StringBuilder();

                while (cursor.moveToNext()) {
                    String ville = cursor.getString(cursor.getColumnIndex("ville"));
                    int nombreAnnonces = cursor.getInt(cursor.getColumnIndex("nombre"));

                    stringBuilder.append("Ville : ").append(ville).append(", Nombre d'annonces : ").append(nombreAnnonces).append("\n");
                }

                textViewNombreAnnonces.setText(stringBuilder.toString());
            } finally {
                // Fermeture du curseur
                cursor.close();
            }
        } else {
            // Affichage d'un message si le curseur est vide
            Toast.makeText(this, "Aucune annonce trouvée.", Toast.LENGTH_SHORT).show();
        }
    }
}