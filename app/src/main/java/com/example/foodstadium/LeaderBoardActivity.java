package com.example.foodstadium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class LeaderBoardActivity extends AppCompatActivity {
    FoodVoteManager voteManager = new FoodVoteManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        TextView returnButton = findViewById(R.id.returnButton);
        ListView itemList = findViewById(R.id.leaderBoardList);
        List<Item> list = voteManager.getTopItems();

        ItemAdapter adapter = new ItemAdapter(this,list);
        itemList.setAdapter(adapter);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LeaderBoardActivity.this, MainActivity.class));
            }
        });
    }



}
