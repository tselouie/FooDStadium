package com.example.foodstadium.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FooDStadium.db";
    private static final int DATABASE_VERSION = 1;
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String VOTES = "votes";
    public static final String CATEGORY = "category";
    public static final String BRANDS_TABLE = "BRANDS";
    public static final String HISTORY_TABLE = "HISTORY";

    private static DatabaseHelper instance = null;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context)   {
        if(instance == null){
            instance = new DatabaseHelper(context);
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createBrands = "CREATE TABLE BRANDS" +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT,"  +
                " name TEXT NOT NULL, "  +
                " votes INTEGER NOT NULL DEFAULT 0," +
                " category TEXT NOT NULL)";
        String createHistory = " CREATE TABLE HISTORY" +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT,"  +
                " name TEXT NOT NULL, "  +
                " timeStamp TEXT NOT NULL," +
                " category TEXT NOT NULL)";


        db.execSQL(createBrands);
        db.execSQL(createHistory);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
