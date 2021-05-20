package com.prideven.android.hungryeats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prideven.android.hungryeats.databinding.CartRvBinding
import java.util.*

class CartAdapter // creating constructor for our adapter class
    (
    // creating variables for our ArrayList and context
    private val cartArrayList: ArrayList<CartFirestore>,
    var cartListener: CartListener
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    var cartRvBinding: CartRvBinding? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        // passing our layout file for displaying our card item
        cartRvBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.cart_rv,
            parent,
            false
        )
        return ViewHolder(cartRvBinding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        // setting data to our text views from our modal class.
        val cart = cartArrayList[position]
        holder.cartRvBinding.itemName.text = cart.itemName
        holder.cartRvBinding.itemPrice.text = cart.itemPrice
        Glide.with(cartRvBinding!!.root.context)
            .load(cart.image)
            .centerCrop()
            .into(holder.cartRvBinding.imageId)
        holder.bindData(
            position,
            CartListener { postion: Int -> cartListener.onItemClick(postion) }
        )
    }

    override fun getItemCount(): Int {
        // returning the size of our array list.
        return cartArrayList.size
    }

    inner class ViewHolder(// creating variables for our text views.
        var cartRvBinding: CartRvBinding
    ) :
        RecyclerView.ViewHolder(cartRvBinding.root) {
        fun bindData(position: Int, cartListener: CartListener) {
            cartRvBinding.delete.setOnClickListener { v: View? ->
                cartListener.onItemClick(
                    position
                )
            }
        }

    }

}