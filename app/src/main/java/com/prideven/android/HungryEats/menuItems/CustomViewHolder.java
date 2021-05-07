package com.prideven.android.hungryeats.menuitems;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.prideven.android.hungryeats.GetCartValuesListener;
import com.prideven.android.hungryeats.R;

/**
 * Provide a reference to the type of views that you are using (custom ViewHolder)
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {

    public CustomViewHolder(View v) {
        super(v);
    }

    public void setData(com.prideven.android.hungryeats.MenuItem menuItem, GetCartValuesListener listener) {

        TextView cal = itemView.findViewById(R.id.cal);
        TextView price = itemView.findViewById(R.id.price);
        TextView item_name = itemView.findViewById(R.id.item_name);
        ImageView image = itemView.findViewById(R.id.image);
        ImageView add_image = itemView.findViewById(R.id.add);

        cal.setText(menuItem.cal);
        price.setText(menuItem.price);
        item_name.setText(menuItem.item_name);

        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(menuItem.item_name,menuItem.price);
            }
        });

    }
}