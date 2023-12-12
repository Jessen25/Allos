package com.example.allos;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    Context context;
    ArrayList<Items> itemList;

    public ItemAdapter(Context context, ArrayList<Items> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemListView = View.inflate(context, R.layout.item_list_view, null);

        ImageView itemImage = itemListView.findViewById(R.id.itemImage);
        TextView itemName = itemListView.findViewById(R.id.itemName);
        ImageView itemAllow = itemListView.findViewById(R.id.itemAllow);

        String drawableName = itemList.get(position).itemImage;
        int drawableResourceId = context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());

        if (drawableResourceId != 0) {
            itemImage.setImageResource(drawableResourceId);
        }

        itemName.setText(itemList.get(position).itemName);

        if (itemList.get(position).isAllowed == true) {
            String allow = "allow";
            int allowId = context.getResources().getIdentifier(allow, "drawable", context.getPackageName());
            itemAllow.setImageResource(allowId);
        } else {
            String disallow = "disallow";
            int disallowId = context.getResources().getIdentifier(disallow, "drawable", context.getPackageName());
            itemAllow.setImageResource(disallowId);
        }

        return itemListView;
    }
}
