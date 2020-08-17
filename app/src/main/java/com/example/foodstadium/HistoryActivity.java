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
import android.widget.Toast;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    FoodVoteManager voteManager = new FoodVoteManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ListView itemList = findViewById(R.id.trendList);
        List<HistoryItem> list = voteManager.getHistory();

        class HistoryItemAdapter extends ArrayAdapter<HistoryItem> {

            private Context context;
            private List<HistoryItem> items;

            public HistoryItemAdapter(Context context, List<HistoryItem> items) {
                super(context, -1,items);
                this.context = context;
                this.items = items;
            }
            @androidx.annotation.NonNull
            @Override
            public View getView(int position, View convertView , @androidx.annotation.NonNull ViewGroup parent){

                if(convertView == null){
                    convertView = LayoutInflater.from(context).inflate(R.layout.history_item_layout,parent,false);
                }

                TextView itemTextView = convertView.findViewById(R.id.historyItemTextView);
                itemTextView.setText(items.get(position).getName());
                TextView itemTimeStamp = convertView.findViewById(R.id.historyItemTimeStamp);
                itemTimeStamp.setText((CharSequence) items.get(position).getTimeStamp());

                return convertView;
            }
        }

       try{
           HistoryItemAdapter adapter = new HistoryItemAdapter(this,list);
           itemList.setAdapter(adapter);
       }catch (Exception e){
           Toast.makeText(this, "No History Data Available.", Toast.LENGTH_SHORT).show();
       }




        TextView returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HistoryActivity.this, MainActivity.class));
            }
        });
    }

}
