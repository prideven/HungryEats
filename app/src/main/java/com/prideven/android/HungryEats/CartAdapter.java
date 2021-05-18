package com.prideven.android.hungryeats;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
        holder.cartRvBinding.itemName.setText(cart.getItemName());
        holder.cartRvBinding.itemPrice.setText(cart.getItemPrice());
        Glide.with(cartRvBinding.getRoot().getContext())
                .load(cart.getImage())
                .centerCrop()
                .into((holder.cartRvBinding.imageId));
//        holder.bindData(position, postion -> {
//            cartArrayList.remove(position);
//            notifyItemRemoved(position);
//        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return cartArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        CartRvBinding cartRvBinding;
        public ViewHolder(@NonNull CartRvBinding cartRvBinding) {
            super(cartRvBinding.getRoot());
            this.cartRvBinding = cartRvBinding;
        }

        public void bindData(int position, CartListener cartListener) {
            cartRvBinding.delete.setOnClickListener(v -> cartListener.onItemClick(position));
        }
    }
}



