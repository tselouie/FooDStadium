package com.example.foodstadium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FoodVoteManager voteManager = new FoodVoteManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton trendButton = findViewById(R.id.trendingButton);
        final ImageButton leaderBoardButton = findViewById(R.id.leaderBoardButton);
        final ImageButton historyButton = findViewById(R.id.historyButton);
        final ImageButton searchButton = findViewById(R.id.searchButton);

        List<Item> list = new ArrayList<Item>();
//Dummy data added into the database
        list.add(new Item(1,"Fruit Loops","cereal"));
        list.add(new Item(2,"Captain Crunch", "cereal"));
        list.add(new Item(3,"Honey Combs", "cereal"));
        list.add(new Item(4,"Frosted Flakes", "cereal"));
        list.add(new Item(5,"Coca Cola", "pop"));
        list.add(new Item(6,"Pepsi", "pop"));
        list.add(new Item(7,"Fanta", "pop"));
        list.add(new Item(8,"Sprite", "pop"));
        list.add(new Item(9,"Mountain Dew", "pop"));
        list.add(new Item(10,"Barq", "pop"));
        list.add(new Item(11,"365", "ketchup"));
        list.add(new Item(12,"Heinz", "ketchup"));
        list.add(new Item(13,"French", "ketchup"));
        list.add(new Item(14,"Hunt's", "ketchup"));
        list.add(new Item(15,"Simply Balanced", "ketchup"));

        list.forEach(voteManager::addItem);

        //Setting button onClick listeners to go to their respective activities
        trendButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, TrendActivity.class));
            }
        });

        leaderBoardButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, LeaderBoardActivity.class));
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });


    }
}
