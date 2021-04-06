package com.prideven.android.hungryeats;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.prideven.android.hungryeats.databinding.RestaurantListFileBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private com.prideven.android.hungryeats.RestaurantViewModel mViewModel;
    private RestaurantListFileBinding mBinding;
    private RecyclerView rv;
    com.prideven.android.hungryeats.RestaurantAdapter ra;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.restaurant_list_file, container, false);

        RecyclerView rv = mBinding.restaurantList;
        List<EatsRestaurantsResponseItem> items = new ArrayList<>();
        ra = new com.prideven.android.hungryeats.RestaurantAdapter(items);
        rv.setAdapter(ra);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        return mBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(com.prideven.android.hungryeats.RestaurantViewModel.class);
        CharSequence zipCode = mBinding.searchZip.getQuery();
        //mViewModel.callRestaurantDataRepo(Integer.parseInt(zipCode.toString()));
        mViewModel.callRestaurantDataRepo(37.42274,-122.139956);


        mViewModel.getRestaurantDetails().observe(getViewLifecycleOwner(), listOfRestaurants ->{
            ra.setData(listOfRestaurants);
            // Update the UI.
        });

        // TODO: Use the ViewModel
    }


}