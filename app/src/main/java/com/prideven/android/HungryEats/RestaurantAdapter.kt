package com.prideven.android.hungryeats

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.prideven.android.hungryeats.databinding.RestaurantLayoutBinding

class RestaurantAdapter(
    var items: List<EatsRestaurantsResponseItem>?,
    private val listener: GetRestaurantIDListener
) : RecyclerView.Adapter<RestaurantViewHolder>() {
    private var rbinding: RestaurantLayoutBinding? =
        null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        rbinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.restaurant_layout,
            parent,
            false
        )
        return RestaurantViewHolder(rbinding)
    }

    override fun onBindViewHolder(
        viewHolder: RestaurantViewHolder,
        position: Int
    ) {
        Log.d(TAG, "Element $position set.")
        viewHolder.setData(items!![position], listener)
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    fun setData(dataSet: List<EatsRestaurantsResponseItem>?) {
        items = dataSet
        notifyDataSetChanged()
    }

    companion object {
        private const val TAG = "RestuarantAdapter"
    }

}