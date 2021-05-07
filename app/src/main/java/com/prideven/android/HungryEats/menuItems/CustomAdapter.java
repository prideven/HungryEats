package com.prideven.android.hungryeats.menuitems;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.prideven.android.hungryeats.GetCartValuesListener;
import com.prideven.android.hungryeats.MenuItem;
import com.prideven.android.hungryeats.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private static final String TAG = "CustomAdapter";

    List<MenuItem> items = new ArrayList<>();
    GetCartValuesListener getCartValuesListener;

    public CustomAdapter(List<MenuItem> dataSet,GetCartValuesListener getCartValuesListener) {
        this.items = dataSet;
        this.getCartValuesListener=getCartValuesListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.restaurant_menu_item_layout, viewGroup, false);

        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");


        viewHolder.setData(items.get(position),getCartValuesListener);
    }

    @Override
    public int getItemCount() {
        if(items == null)
            return 0;
        return items.size();
    }
}