package com.prideven.android.HungryEats;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Provide a reference to the type of views that you are using (custom ViewHolder)
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {

    public CustomViewHolder(View v) {
        super(v);
    }

    public void setData(MenuItem menuItem) {

        TextView  cal = itemView.findViewById(R.id.cal);
        TextView price =itemView.findViewById(R.id.price);
        TextView item_name =itemView.findViewById(R.id.item_name);
        ImageView image =itemView.findViewById(R.id.image);
        ImageView add_image =itemView.findViewById(R.id.add);

        cal.setText(menuItem.cal);
        price.setText(menuItem.price );
        item_name.setText(menuItem.item_name);
    }
}
