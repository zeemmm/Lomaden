package com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbUsers extends SQLiteOpenHelper {
    private static final String DBName = "lomaden.db";
    private static final int version = 1;
    private static final String table = "user";
    private static final String key_id = "id";
    private static final String key_username = "username";
    private static final String key_password = "password";
    private static final String key_address = "address";
    private static final String key_email = "email";
    private static final String key_number = "number";
    private SQLiteDatabase db;

    public dbUsers(Context context) {
        super(context, DBName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + table + "(" + key_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + key_username + " TEXT NULL, "+key_address+" TEXT NULL, " + key_password + " TEXT NULL, " + key_email + " TEXT NULL, " + key_number + " TEXT NULL, waktu DATETIME DEFAULT CURRENT_TIMESTAMP)";
        sqLiteDatabase.execSQL(query);
        System.out.println("create table " + table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+table);
        System.out.println("drop table "+table+" success");
        onCreate(sqLiteDatabase);
    }

    public boolean login(String email, String password) {
        db = this.getReadableDatabase();
        String[] columns = {key_id};
        //sql where email = {email} and password = {password}
        String selection = key_email + " = ? AND " + key_password + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(table, columns, selection, selectionArgs, null, null, null);
        boolean success = cursor.moveToFirst();
        cursor.close();
        return success;
    }

    public long register(String username,String address, String email, String number, String pass) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(key_username, username);
        values.put(key_address, address);
        values.put(key_email, email);
        values.put(key_number, number);
        values.put(key_password, pass);

        long result = db.insert(table, null, values);
        return result;
    }

    public String getUser(String email, String password){
        db = this.getReadableDatabase();
        String[] data = {
                key_id,
                key_username,
                key_email,
                key_number,
                key_address
        };

        String selection = key_email + " = ? AND "+key_password + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(table, data, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int usernameIndex = cursor.getColumnIndex(key_username);
            String username = cursor.getString(usernameIndex);
            cursor.close();
            return username;
        }
        return null;
    }

    public String getWaktu(String email, String password){
        db = this.getReadableDatabase();
        String[] data = {
                key_id,
                key_username,
                key_email,
                key_number,
                key_address,
                "waktu"
        };

        String selection = key_email + " = ? AND "+key_password + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(table, data, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int waktu = cursor.getColumnIndex("waktu");
            String username = cursor.getString(waktu);
            cursor.close();
            return username;
        }
        return null;
    }
    public String getNumber(String email, String password){
        db = this.getReadableDatabase();
        String[] data = {
                key_id,
                key_username,
                key_email,
                key_number,
                key_address,
                "waktu"
        };

        String selection = key_email + " = ? AND "+key_password + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(table, data, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int waktu = cursor.getColumnIndex(key_number);
            String username = cursor.getString(waktu);
            cursor.close();
            return username;
        }
        return null;
    }
    public String getEmail(String email, String password){
        db = this.getReadableDatabase();
        String[] data = {
                key_id,
                key_username,
                key_email,
                key_number,
                key_address,
                "waktu"
        };

        String selection = key_email + " = ? AND "+key_password + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(table, data, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int waktu = cursor.getColumnIndex(key_email);
            String username = cursor.getString(waktu);
            cursor.close();
            return username;
        }
        return null;
    }

    public String getAddress(String email, String password){
        db = this.getReadableDatabase();
        String[] data = {
                key_id,
                key_username,
                key_email,
                key_number,
                key_address,
                "waktu"
        };

        String selection = key_email + " = ? AND "+key_password + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(table, data, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int waktu = cursor.getColumnIndex(key_address);
            String username = cursor.getString(waktu);
            cursor.close();
            return username;
        }
        return null;
    }

}
