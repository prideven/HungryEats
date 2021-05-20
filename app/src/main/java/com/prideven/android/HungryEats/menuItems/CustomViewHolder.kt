package com.prideven.android.hungryeats.menuitems

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prideven.android.hungryeats.GetCartValuesListener
import com.prideven.android.hungryeats.MenuItem
import com.prideven.android.hungryeats.R
import com.prideven.android.hungryeats.databinding.RestaurantMenuItemLayoutBinding

/**
 * Provide a reference to the type of views that you are using (custom ViewHolder)
 */
class CustomViewHolder(v: View?) : RecyclerView.ViewHolder(v!!) {
    var mbinding: RestaurantMenuItemLayoutBinding? =
        null

    fun setData(
        menuItem: MenuItem,
        listener: GetCartValuesListener
    ) {
        val cal = itemView.findViewById<TextView>(R.id.cal)
        val price = itemView.findViewById<TextView>(R.id.price)
        val item_name = itemView.findViewById<TextView>(R.id.item_name)
        val itemimage =
            itemView.findViewById<ImageView>(R.id.add_image)
        val add_image =
            itemView.findViewById<ImageView>(R.id.add)
        cal.text = menuItem.cal
        price.text = menuItem.price
        item_name.text = menuItem.item_name
        Glide.with(cal.context)
            .load(menuItem.image)
            .centerCrop()
            .into(itemimage)
        add_image.setOnClickListener {
            listener.onItemClick(
                menuItem.item_name,
                menuItem.price,
                menuItem.image
            )
        }
    }
}