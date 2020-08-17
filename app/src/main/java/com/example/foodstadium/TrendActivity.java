package com.example.foodstadium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class TrendActivity extends AppCompatActivity {
    FoodVoteManager voteManager = new FoodVoteManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend);
        TextView returnButton = findViewById(R.id.returnButton);
        ListView itemList = findViewById(R.id.trendList);
        List<String> list = voteManager.getCategories();
        class TrendAdapter extends ArrayAdapter<String> {

            private Context context;
            private List<String> items;

            public TrendAdapter(Context context, List<String> items) {
                super(context, -1,items);
                this.context = context;
                this.items = items;
            }

            @androidx.annotation.NonNull
            @Override
            public View getView(int position, View convertView , @androidx.annotation.NonNull ViewGroup parent){
                if(convertView == null){
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
                }

                TextView itemTextView = convertView.findViewById(R.id.itemTextView);
                itemTextView.setText(items.get(position));
                return convertView;
            }
        }
        TrendAdapter adapter = new TrendAdapter(this,list);
        itemList.setAdapter(adapter);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrendActivity.this, MainActivity.class));
            }
        });
    }
}
