package com.prideven.android.hungryeats;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.prideven.android.hungryeats.databinding.CartRvBinding;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {


    // creating variables for our ArrayList and context
    private ArrayList<CartFirestore> cartArrayList;
    public CartRvBinding cartRvBinding;

    // creating constructor for our adapter class
    public CartAdapter(ArrayList<CartFirestore> cartArrayList) {
        this.cartArrayList = cartArrayList;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        cartRvBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.cart_rv,
                        parent,
                        false);
        return new ViewHolder(cartRvBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        CartFirestore cart = cartArrayList.get(position);
        holder.itemName.setText(cart.getItemName());
        holder.itemPrice.setText(cart.getItemPrice());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return cartArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private final TextView itemName;
        private final TextView itemPrice;

        public ViewHolder(@NonNull CartRvBinding cartRvBinding) {
            super(cartRvBinding.getRoot());
            // initializing our text views.
            itemName = cartRvBinding.itemName;
            itemPrice = cartRvBinding.itemPrice;
        }
    }
}



