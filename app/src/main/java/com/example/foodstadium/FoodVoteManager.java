package com.example.foodstadium;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodstadium.domain.DatabaseHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*This class manages all database function calls for our Activity views*/
public class FoodVoteManager {
    private DatabaseHelper dbHelper;
    ArrayList<HistoryItem> historyItems = new ArrayList<HistoryItem>();
    public FoodVoteManager(Context context)    {
        dbHelper = DatabaseHelper.getInstance(context);

    }
//Gets the top 5 Items sorted by votes desc
    public List<Item> getTopItems(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + DatabaseHelper.BRANDS_TABLE +" ORDER BY " + DatabaseHelper.VOTES + " DESC LIMIT 5", null
        );

        List<Item> items = new ArrayList<>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                Item item = new Item(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.ID)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.VOTES)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME)),
                        true,
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.CATEGORY))
                );
                items.add(item);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return items;
    }
//Gets all items for a particular category
    public List<Item> getItems(String category){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + DatabaseHelper.BRANDS_TABLE + " WHERE category= \"" + category + "\"" , null
        );

        List<Item> items = new ArrayList<>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                Item item = new Item(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.ID)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.VOTES)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME)),
                        true,
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.CATEGORY))
                );
                items.add(item);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return items;
    }
    //Checks if an item is in the database
    public boolean isDataInDB(String tableName, String dbField, String fieldValue) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "Select * from " + tableName + " WHERE " + dbField + " = \"" + fieldValue + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
//Adds item to database
    public void addItem(Item item){
        if(isDataInDB(DatabaseHelper.BRANDS_TABLE,DatabaseHelper.NAME,item.getName())){
            System.out.println("DATA already exists in Database.");
        }else{
            ContentValues newItem = new ContentValues();
            newItem.put(DatabaseHelper.NAME,item.getName()  );
            newItem.put(DatabaseHelper.VOTES,item.getVotes());
            newItem.put(DatabaseHelper.CATEGORY,item.getCategory());
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.insert(DatabaseHelper.BRANDS_TABLE,null, newItem);
        }
    }
//Increases the vote by 1 in database for a particular item ID
    public void updateVote(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query ="UPDATE " + DatabaseHelper.BRANDS_TABLE + " SET votes = votes + " + 1 + " WHERE _id = " + id;
        db.execSQL(query);
    }
//adds to history table when vote is updated
    public void addToHistory(HistoryItem item){
        ContentValues newItem = new ContentValues();
        newItem.put(DatabaseHelper.NAME,item.getName()  );
        newItem.put("timeStamp",item.getTimeStamp());
        newItem.put(DatabaseHelper.CATEGORY,item.getCategory());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(DatabaseHelper.HISTORY_TABLE,null, newItem);

    }
//Retrieves the history and displays it in a Listview
    public List<HistoryItem> getHistory(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + DatabaseHelper.HISTORY_TABLE, null
        );

        List<HistoryItem> items = new ArrayList<>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                Date timeStamp = new Date(cursor.getString(cursor.getColumnIndex("timeStamp")));
                HistoryItem item = new HistoryItem(
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.CATEGORY)),
                        timeStamp);
                items.add(item);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return items;
    }
//Gets the categories for trending page
    public List<String> getCategories(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT DISTINCT category FROM " + DatabaseHelper.BRANDS_TABLE, null
        );

        List<String> items = new ArrayList<>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                items.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.CATEGORY)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return items;
    }


}
