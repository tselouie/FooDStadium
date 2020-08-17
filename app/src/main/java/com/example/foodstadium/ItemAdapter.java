package com.example.foodstadium;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
/*Generic adapter used to show Items, currently only used by LeaderBoard*/
public class ItemAdapter extends ArrayAdapter<Item> {

    private Context context;
    private List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
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
        itemTextView.setText(items.get(position).getName());

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        return convertView;
    }
}