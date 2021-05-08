package com.prideven.android.hungryeats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.prideven.android.hungryeats.databinding.AddToCartBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public AddToCartBinding addToCartBinding;


    private RecyclerView cartRV;
    private ArrayList<CartFirestore> cartArrayList;
    private CartAdapter cartAdapter;
    public FirebaseFirestore db;


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
    public static CartFragment newInstance() {
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

        addToCartBinding = DataBindingUtil.inflate(inflater, R.layout.add_to_cart, container, false);

        cartRV = addToCartBinding.cartDetails;
        db = FirebaseFirestore.getInstance();

        // creating our new array list
        cartArrayList = new ArrayList<>();
        cartRV.setHasFixedSize(true);
        cartRV.setLayoutManager(new LinearLayoutManager(requireContext()));

        // adding our array list to our recycler view adapter class.
        cartAdapter = new CartAdapter(cartArrayList);

        // setting adapter to our recycler view.
        cartRV.setAdapter(cartAdapter);

        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("CartDetails").whereEqualTo("userId",userId).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are
                            // hiding our progress bar and adding
                            // our data in a list.
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing
                                // that list to our object class.
                                CartFirestore c = d.toObject(CartFirestore.class);

                                // and we will pass this object class
                                // inside our arraylist which we have
                                // created for recycler view.
                                cartArrayList.add(c);
                            }
                            // after adding the data to recycler view.
                            // we are calling recycler view notifuDataSetChanged
                            // method to notify that data has been changed in recycler view.
                            cartAdapter.notifyDataSetChanged();
                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(getContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // if we do not get any data or any error we are displaying
                // a toast message that we do not get any data
                Toast.makeText(getContext(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
            }
        });
        return addToCartBinding.getRoot();

    }
}