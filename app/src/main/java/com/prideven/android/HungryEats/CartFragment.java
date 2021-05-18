package com.prideven.android.hungryeats;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.prideven.android.hungryeats.databinding.AddToCartBinding;
import com.prideven.android.hungryeats.databinding.CartRvBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public int total=0;
    public CartRvBinding cartRvBinding;




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
        cartRvBinding=DataBindingUtil.inflate(inflater,R.layout.cart_rv,container,false);
        cartRV = addToCartBinding.cartDetails;

        db = FirebaseFirestore.getInstance();


        // creating our new array list
        cartArrayList = new ArrayList<>();
        cartRV.setHasFixedSize(true);
        cartRV.setLayoutManager(new LinearLayoutManager(requireContext()));

        // adding our array list to our recycler view adapter class.

//        cartAdapter = new CartAdapter(cartArrayList);

        cartAdapter = new CartAdapter(cartArrayList,new CartListener(){
            public void onItemClick(int pos) {
                if(!cartArrayList.isEmpty()  && pos < cartArrayList.size()) {
                    findnewTotal(pos);
                    cartArrayList.remove(pos);
                    cartAdapter.notifyItemRemoved(pos);
                }
                Toast.makeText(getContext(), "Item deleted from cart", Toast.LENGTH_SHORT).show();
            }
        });

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
                                ;
                                // after getting this list we are passing
                                // that list to our object class.
                                CartFirestore c = d.toObject(CartFirestore.class);
                                c.setId(d.getId());
                                // and we will pass this object class
                                // inside our arraylist which we have
                                // created for recycler view.
                                total+=Integer.parseInt(c.getItemPrice().replace("$",""));
                                cartArrayList.add(c);
                            }
                            // after adding the data to recycler view.
                            // we are calling recycler view notifuDataSetChanged
                            // method to notify that data has been changed in recycler view.
                            cartAdapter.notifyDataSetChanged();

                            addToCartBinding.tPrice.setText("$"+total);


                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(getContext(), "No data found in cart", Toast.LENGTH_SHORT).show();
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



        addToCartBinding.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getContext(), "Order is placed successfully", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager =
                        Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,RestaurantFragment.newInstance());
                fragmentTransaction.commit();
            }
        });
        return addToCartBinding.getRoot();

    }

    private void deleteItem(int pos) {

            // below line is for getting the collection
            // where we are storing our courses.
            db.collection("CartDetails").
                    // after that we are getting the document
                    // which we have to delete.

                            document(itemName).

                    // after passing the document id we are calling
                    // delete method to delete this document.
                            delete().
                    // after deleting call on complete listener
                    // method to delete this data.
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // inside on complete method we are checking
                            // if the task is success or not.
                            if (task.isSuccessful()) {
                                // this method is called when the task is success
                                // after deleting we are starting our MainActivity.
                                Toast.makeText(getContext(), "Item has been deleted from cart.", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getContext(), CartFragment.class);
                                startActivity(i);
                            } else {
                                // if the delete operation is failed
                                // we are displaying a toast message.
                                Toast.makeText(getContext(), "Fail to delete the item. ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    public boolean onOptionsItemSelected(@NonNull android.view.MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void findnewTotal(int pos){
        String val=cartArrayList.get(pos).getItemPrice();
        int new_val=Integer.parseInt(val.replace("$",""));
        int new_total=total-new_val;
        addToCartBinding.tPrice.setText("$"+new_total);
    }


}





