package com.prideven.android.hungryeats;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.location.Address;

import com.google.gson.Gson;
import com.prideven.android.hungryeats.databinding.RestaurantListFileBinding;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {

    private com.prideven.android.hungryeats.RestaurantViewModel mViewModel;
    private RestaurantListFileBinding mBinding;
    private RecyclerView rv;
    com.prideven.android.hungryeats.RestaurantAdapter ra;

    private Double lat;
    private Double lng;
    final String zip = "95192";

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

        final Geocoder geocoder = new Geocoder(getContext());
        try {
            List<Address> addresses = geocoder.getFromLocationName(zip, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                // Use the address as needed
                String message = String.format("Latitude: %f, Longitude: %f",
                        address.getLatitude(), address.getLongitude());

                lat = address.getLatitude();
                lng = address.getLongitude();

                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            } else {
                // Display appropriate message when Geocoder services are not available
                Toast.makeText(getContext(), "Unable to geocode zipcode", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            // handle exception
        }

        //end of test code
        mViewModel = new ViewModelProvider(this).get(com.prideven.android.hungryeats.RestaurantViewModel.class);
        // CharSequence zipCode = mBinding.searchZip.getQuery();
        //mViewModel.callRestaurantDataRepo(Integer.parseInt(zipCode.toString()));
        mViewModel.callRestaurantDataRepo(lat,lng);


        mViewModel.getRestaurantDetails().observe(getViewLifecycleOwner(), listOfRestaurants ->{
            ra.setData(listOfRestaurants);
            // Update the UI.
        });

        // TODO: Use the ViewModel
    }

}