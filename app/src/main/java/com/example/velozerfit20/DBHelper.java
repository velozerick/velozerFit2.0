package com.example.velozerfit20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Velocirfit.db";
    private static final int DATABASE_VERSION = 2; // ¡Actualizado para incluir género!

    // Tabla y columnas
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_GENDER = "gender"; // Nombre consistente (usa "gender" o "genero" en todo el código)

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery =
                "CREATE TABLE " + TABLE_USERS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_USERNAME + " TEXT UNIQUE, " + // Evita usuarios duplicados
                        COLUMN_EMAIL + " TEXT UNIQUE, " +
                        COLUMN_PASSWORD + " TEXT, " +
                        COLUMN_GENDER + " TEXT" +
                        ");";
        db.execSQL(createTableQuery);
        Log.d("DBHelper", "Tabla creada correctamente");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_USERS + " ADD COLUMN " + COLUMN_GENDER + " TEXT");
            Log.d("DBHelper", "Base de datos actualizada a versión 2 (género añadido)");
        }
    }

    // Método para insertar usuario (con manejo de errores)
    public boolean addUser(String username, String email, String password, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_USERNAME, username);
            cv.put(COLUMN_EMAIL, email);
            cv.put(COLUMN_PASSWORD, password);
            cv.put(COLUMN_GENDER, gender);

            long result = db.insert(TABLE_USERS, null, cv);
            if (result == -1) {
                Log.e("DBHelper", "Error al insertar usuario");
                return false;
            }
            Log.d("DBHelper", "Usuario registrado: " + username);
            return true;
        } catch (Exception e) {
            Log.e("DBHelper", "Error en addUser: " + e.getMessage());
            return false;
        } finally {
            db.close();
        }
    }

    // Verifica credenciales (optimizado)
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String query =
                    "SELECT * FROM " + TABLE_USERS +
                            " WHERE " + COLUMN_USERNAME + " = ? AND " +
                            COLUMN_PASSWORD + " = ?";
            cursor = db.rawQuery(query, new String[]{username, password});
            return cursor.getCount() > 0;
        } catch (Exception e) {
            Log.e("DBHelper", "Error en checkUser: " + e.getMessage());
            return false;
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
    }

    // Obtiene el género del usuario (seguro)
    public String getUserGender(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String query =
                    "SELECT " + COLUMN_GENDER + " FROM " + TABLE_USERS +
                            " WHERE " + COLUMN_USERNAME + " = ?";
            cursor = db.rawQuery(query, new String[]{username});

            if (cursor.moveToFirst()) {
                return cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER));
            }
            return null;
        } catch (Exception e) {
            Log.e("DBHelper", "Error en getUserGender: " + e.getMessage());
            return null;
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
    }
}