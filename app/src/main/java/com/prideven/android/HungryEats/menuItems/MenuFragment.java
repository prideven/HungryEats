package com.prideven.android.hungryeats.menuitems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.google.gson.Gson;
import com.prideven.android.hungryeats.R;
import com.prideven.android.hungryeats.databinding.MenuFragmentBinding;

import java.lang.reflect.Type;
import java.util.List;

public class MenuFragment extends Fragment {

    private MenuViewModel mViewModel;
    public MenuFragmentBinding mBinding;
    public MenuAdapter ma;
    private RecyclerView rv;
    public int id=10;
    private MutableLiveData<EatsMenuResponse> eatsMenuResponse;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.menu_fragment, container, false);
        RecyclerView rv = mBinding.menuItems;
        com.prideven.android.hungryeats.CustomAdapter ma = new com.prideven.android.hungryeats.CustomAdapter(dataSet());
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
        mViewModel.callMenuDataRepo(id);


        mViewModel.getMenuDetails().observe(getViewLifecycleOwner(), listOfMenu ->{
            mBinding.menuDescription.setText(listOfMenu.getDescription());
            mBinding.rating.setText("Rating: "+listOfMenu.getAverage_rating());
            mBinding.restaurntName.setText(listOfMenu.getName());
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
}

