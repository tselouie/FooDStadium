package com.example.foodstadium;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.foodstadium.domain.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    FoodVoteManager voteManager = new FoodVoteManager(this);
    List<Item> list = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
//Have yet to find a good API to search for brands.. temporarily using  cereal as placeholder..
        ListView itemList = findViewById(R.id.searched_item_list);
        AppCompatButton searchButton = findViewById(R.id.searchButton);

        SearchItemAdapter adapter = new SearchItemAdapter(this,list);
        itemList.setAdapter(adapter);
        searchButton.setOnClickListener(v -> onSearchButtonClick());

    }
//search field is case insensitive, will display foods as soon as it is found in database
    private void onSearchButtonClick(){
        EditText inputText = (EditText) findViewById(R.id.searchText);
        String searchValue = inputText.getText().toString().trim().toLowerCase();
        System.out.println("Search TEXT:" + searchValue);
        ListView itemList = findViewById(R.id.searched_item_list);
        if(voteManager.isDataInDB(DatabaseHelper.BRANDS_TABLE, "category",searchValue)){
            list = voteManager.getItems(searchValue);
           SearchItemAdapter adapter = new SearchItemAdapter(this,list);
            itemList.setAdapter(adapter);
            itemList.setVisibility(ListView.VISIBLE);
        }else{
            Toast noCategory = Toast.makeText(getApplicationContext(),"No category Found.",Toast.LENGTH_SHORT);
            itemList.setVisibility(ListView.INVISIBLE);
            noCategory.show();
        };
    }
    class SearchItemAdapter extends ArrayAdapter<Item> {

        private Context context;
        private List<Item> items;

        public SearchItemAdapter(Context context, List<Item> items) {
            super(context, -1,items);
            this.context = context;
            this.items = items;
        }

        @Override
        public int getCount()   {
            return items.size();
        }

        @androidx.annotation.NonNull
        @Override
        public View getView(int position, View convertView , @androidx.annotation.NonNull ViewGroup parent){
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);

            }

            TextView itemTextView = convertView.findViewById(R.id.itemTextView);
            itemTextView.setText(items.get(position).getName());

            View.OnClickListener onClickListener = v -> {
                System.out.println(items.get(position).getName() + "isClicked!");
                voteManager.updateVote(items.get(position).getId());
                HistoryItem history = new HistoryItem(items.get(position).getName(),items.get(position).getCategory());
                voteManager.addToHistory(history);
                startActivity(new Intent(SearchActivity.this, LeaderBoardActivity.class));
            };
            convertView.setOnClickListener(onClickListener);

            return convertView;
        }

    }


}
