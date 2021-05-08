package com.prideven.android.hungryeats.menuitems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.prideven.android.hungryeats.CartFirestore;
import com.prideven.android.hungryeats.GetCartValuesListener;
import com.prideven.android.hungryeats.R;
import com.prideven.android.hungryeats.databinding.MenuFragmentBinding;

import java.lang.reflect.Type;
import java.util.List;

public class MenuFragment extends Fragment {

    private MenuViewModel mViewModel;
    private MenuFragmentBinding mBinding;
    public MenuAdapter ma;
    private RecyclerView rv;
    public int id;
    public static final String RESTAURANT_ID = "restaurantIndex";
    public String itemName, itemPrice, userId;
    private FirebaseFirestore db;

    private String restaurantName;
    private MutableLiveData<EatsMenuResponse> eatsMenuResponse;

    public static MenuFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(RESTAURANT_ID, id);
        MenuFragment menuFragment = new MenuFragment();
        menuFragment.setArguments(bundle);
        return menuFragment;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.menu_fragment, container, false);
        RecyclerView rv = mBinding.menuItems;
        db = FirebaseFirestore.getInstance();
        CustomAdapter ma = new CustomAdapter(dataSet(),
                new GetCartValuesListener() {
                    public void onItemClick(String itemName, String itemPrice) {
                        userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
                        addDataToFirestore(itemName, itemPrice, userId);
                    }
                }
        );
        rv.setAdapter(ma);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        return mBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MenuViewModel.class);

        // CharSequence zipCode = mBinding.searchZip.getQuery();
        //mViewModel.callRestaurantDataRepo(Integer.parseInt(zipCode.toString()));
        if (getArguments() != null) {
            id = getArguments().getInt(RESTAURANT_ID, -1);
        }
        mViewModel.callMenuDataRepo(id);


        mViewModel.getMenuDetails().observe(getViewLifecycleOwner(), listOfMenu -> {
            mBinding.menuDescription.setText(listOfMenu.getDescription());
            mBinding.rating.setText("Rating: " + listOfMenu.getAverage_rating());
            restaurantName = listOfMenu.getName();
            mBinding.restaurntName.setText(restaurantName);
            getActivity().setTitle(restaurantName);
            Glide.with(mBinding.getRoot().getContext())
                    .load(listOfMenu.getCover_img_url())
                    .centerCrop()
                    .into((mBinding.mcdImg));
            // Update the UI.
        });

        // TODO: Use the ViewModel
    }


    public List<com.prideven.android.hungryeats.MenuItem> dataSet() {
        Gson gson = new Gson();

        com.prideven.android.hungryeats.Root root = gson.fromJson(menuItemData, (Type) com.prideven.android.hungryeats.Root.class);
        return root.menu_item;
    }

    static String menuItemData = "{\n" +
            "  \"menu_item\": [\n" +
            "\n" +
            "    {\n" +
            "      \"item_name\": \"French Fries\",\n" +
            "      \"cal\": \"220cal\",\n" +
            "      \"price\": \"$8\",\n" +
            "      \"image\": \"https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"item_name\": \"Veg Burger\",\n" +
            "      \"cal\": \"420cal\",\n" +
            "      \"price\": \"$15\",\n" +
            "      \"image\": \"https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"item_name\": \"Chicken Burger\",\n" +
            "      \"cal\": \"520cal\",\n" +
            "      \"price\": \"$18\",\n" +
            "      \"image\": \"https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"item_name\": \"Nuggets\",\n" +
            "      \"cal\": \"920cal\",\n" +
            "      \"price\": \"$19\",\n" +
            "      \"image\": \"https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"item_name\": \"Coke\",\n" +
            "      \"cal\": \"220cal\",\n" +
            "      \"price\": \"$8\",\n" +
            "      \"image\": \"https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"item_name\": \"Egg Burger\",\n" +
            "      \"cal\": \"320cal\",\n" +
            "      \"price\": \"$12\",\n" +
            "      \"image\": \"https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"item_name\": \"Meals\",\n" +
            "      \"cal\": \"920cal\",\n" +
            "      \"price\": \"$22\",\n" +
            "      \"image\": \"https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg\"\n" +
            "    }\n" +
            "  ]\n" +
            "}\n" +
            "\n";


    private void addDataToFirestore(String itemName, String itemPrice, String userId) {

        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference dbCart = db.collection("CartDetails");

        // adding our data to our cart object class.
        CartFirestore cartFirestore = new CartFirestore(itemName, itemPrice, userId);

        // below method is use to add data to Firebase Firestore.
        dbCart.add(cartFirestore).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(getActivity(), "Your item has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(getActivity(), "Fail to add item \n" + e, Toast.LENGTH_SHORT).show();
            }
        });

    }
}

