package com.example.pjt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBconnexion extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user_accounts.db";
    private static final int DATABASE_VERSION = 1;

    // Table name for user accounts
    private static final String TABLE_USER_ACCOUNTS = "user_accounts";

    // Column names for user accounts
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    // SQL query to create the user accounts table
    private static final String SQL_CREATE_TABLE_USER_ACCOUNTS =
            "CREATE TABLE " + TABLE_USER_ACCOUNTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT)";

    // Table name for annonces
    private static final String TABLE_ANNONCES = "annonces";

    // Column names for annonces
    private static final String COLUMN_ANNONCE_ID = "_id";
    private static final String COLUMN_ANNONCE_TITRE = "titre";
    private static final String COLUMN_ANNONCE_CATEGORIE = "categorie";
    private static final String COLUMN_ANNONCE_SECTEUR = "secteur";
    private static final String COLUMN_ANNONCE_DESCRIPTION = "description";
    private static final String COLUMN_ANNONCE_VILLE = "ville";

    // SQL query to create the annonces table
    private static final String SQL_CREATE_TABLE_ANNONCES =
            "CREATE TABLE " + TABLE_ANNONCES + " (" +
                    COLUMN_ANNONCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ANNONCE_TITRE + " TEXT, " +
                    COLUMN_ANNONCE_CATEGORIE + " TEXT, " +
                    COLUMN_ANNONCE_SECTEUR + " TEXT, " +
                    COLUMN_ANNONCE_DESCRIPTION + " TEXT, " +
                    COLUMN_ANNONCE_VILLE + " TEXT)";

    public DBconnexion(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the user accounts table
        db.execSQL(SQL_CREATE_TABLE_USER_ACCOUNTS);
        // Create the annonces table
        db.execSQL(SQL_CREATE_TABLE_ANNONCES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the tables if they exist and recreate them
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANNONCES);
        onCreate(db);
    }

    // Method to insert user account data into the user accounts table
    public long insertUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        long id = db.insert(TABLE_USER_ACCOUNTS, null, values);
        db.close();
        return id;
    }

    // Method to insert annonce data into the annonces table
    public long insertAnnonce(String titre, String categorie, String secteur, String description, String ville) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ANNONCE_TITRE, titre);
        values.put(COLUMN_ANNONCE_CATEGORIE, categorie);
        values.put(COLUMN_ANNONCE_SECTEUR, secteur);
        values.put(COLUMN_ANNONCE_DESCRIPTION, description);
        values.put(COLUMN_ANNONCE_VILLE, ville);
        long id = db.insert(TABLE_ANNONCES, null, values);
        db.close();
        return id;
    }


    // Méthode pour vérifier si l'utilisateur existe dans la base de données
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_USER_ACCOUNTS +
                    " WHERE " + COLUMN_EMAIL + " = ? AND " +
                    COLUMN_PASSWORD + " = ?", new String[]{email, password});
            return cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }
    public Cursor getNombreAnnoncesParVille() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT ville, COUNT(*) as nombre FROM annonces GROUP BY ville";
        return db.rawQuery(query, null);
    }
}