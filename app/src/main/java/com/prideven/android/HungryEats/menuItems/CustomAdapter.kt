package com.prideven.android.hungryeats.menuitems

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prideven.android.hungryeats.GetCartValuesListener
import com.prideven.android.hungryeats.MenuItem
import com.prideven.android.hungryeats.R
import java.util.*

class CustomAdapter(
    dataSet: List<MenuItem>?,
    getCartValuesListener: GetCartValuesListener
) : RecyclerView.Adapter<CustomViewHolder>() {
    var items: List<MenuItem>? =
        ArrayList()
    var getCartValuesListener: GetCartValuesListener
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CustomViewHolder {
        // Create a new view.
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.restaurant_menu_item_layout, viewGroup, false)
        return CustomViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: CustomViewHolder, position: Int) {
        Log.d(TAG, "Element $position set.")
        viewHolder.setData(items!![position], getCartValuesListener)
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    companion object {
        private const val TAG = "CustomAdapter"
    }

    init {
        items = dataSet
        this.getCartValuesListener = getCartValuesListener
    }
}