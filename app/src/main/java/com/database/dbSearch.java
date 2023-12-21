package com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.data.DataSet;
import com.example.lomaden.adapter.SearchAdapter;

import java.util.ArrayList;

public class dbSearch extends SQLiteOpenHelper {

    private final static String DBName = "lomaden.db";
    private final static int version = 1;
    private final static String tabel = "history_pencarian";
    private final static String key_id = "id";
    private final static String key_pencarian = "pencarian";
    static SQLiteDatabase db;

    public dbSearch(Context context){
        super(context, DBName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + tabel + "(" + key_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + key_pencarian + " TEXT NULL, " + "waktu DATETIME DEFAULT CURRENT_TIMESTAMP)";
        sqLiteDatabase.execSQL(query);
        System.out.println("create table "+tabel+" success");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tabel);
        System.out.println("drop table "+tabel+" success");
        onCreate(sqLiteDatabase);
    }

    public long addSearch(String search){
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(key_pencarian, search);

        return db.insert(tabel, null, contentValues);
    }

    public boolean showColumns() {
        db = this.getReadableDatabase();
        String[] data = {
                key_id,
                key_pencarian
        };

        Cursor cursor = db.query(tabel, data, null, null, null, null, null);
        int eId = cursor.getColumnIndex(key_id);
        int ePencarian = cursor.getColumnIndex(key_pencarian);

        boolean allColumnsFilled = true; // Flag to track if all columns have values

        StringBuilder output = new StringBuilder();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String id = cursor.getString(eId);
            String pencarian = cursor.getString(ePencarian);

            if (id.isEmpty() || pencarian.isEmpty()) {
                allColumnsFilled = false; // Set the flag to false if any column is empty
            }

            output.append("id: ").append(id).append("\n")
                    .append("search: ").append(pencarian).append("\n");
        }

        cursor.close();

        System.out.println(output.toString()); // Print the output if needed

        return allColumnsFilled;
    }


    public void showColumnsWithArrayDataSet(ArrayList<DataSet> dataSet, SearchAdapter adapter){
        db = this.getReadableDatabase();
        String[] data = {
                key_id,
                key_pencarian
        };

        Cursor cursor = db.query(tabel, data, null, null, null, null, null);
        int eId = cursor.getColumnIndex(key_id);
        int ePencarian = cursor.getColumnIndex(key_pencarian);


        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            dataSet.add(new DataSet(Integer.parseInt(cursor.getString(eId)),cursor.getString(ePencarian)));
        }

        adapter.notifyDataSetChanged();
    }

    public void delete(long id){
        db = this.getWritableDatabase();
        int delete = db.delete(tabel, key_id + " = " + id, null);
        System.out.println("delete columns "+delete);
    }
}
