package com.prideven.android.hungryeats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.prideven.android.hungryeats.databinding.AddToCartBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public AddToCartBinding addToCartBinding;


    // TODO: Rename and change types of parameters
    private String mParam1;
    public String itemName;
    public int price;
    public static final String ITEM_NAME = "itemName";
    public static final String ITEM_PRICE = "itemPrice";
    public CartFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance( String itemName,String price) {
        CartFragment fragment = new CartFragment();
        Bundle bundle = new Bundle();
        CartFragment cartFragment = new CartFragment();
        cartFragment.setArguments(bundle);
        return cartFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_to_cart, container, false);
    }
}