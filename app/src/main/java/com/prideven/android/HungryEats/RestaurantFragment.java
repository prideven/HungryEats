package com.prideven.android.hungryeats;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prideven.android.hungryeats.databinding.RestaurantLayoutBinding;
import com.prideven.android.hungryeats.databinding.RestaurantListFileBinding;
import com.prideven.android.hungryeats.menuitems.MenuFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RestaurantFragment extends Fragment{

    private com.prideven.android.hungryeats.RestaurantViewModel mViewModel;
    private RestaurantListFileBinding mBinding;
    private RestaurantLayoutBinding rBinding;
    private RecyclerView rv;
    com.prideven.android.hungryeats.RestaurantAdapter ra;
    private Double lat;
    private Double lng;
    final String zip = "95192";
    public int id;
    public static RestaurantFragment newInstance() {
        return new RestaurantFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.restaurant_list_file, container, false);
        RecyclerView rv = mBinding.restaurantList;
        List<EatsRestaurantsResponseItem> items = new ArrayList<>();
        ra = new com.prideven.android.hungryeats.RestaurantAdapter(items,
                new GetRestaurantIDListener(){
                    public void onItemClick(int Id){
                        MenuFragment menuFragment = MenuFragment.newInstance(Id);
                        FragmentManager fragmentManager =
                                Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container, menuFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });

        rv.setAdapter(ra);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        return mBinding.getRoot();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

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
        mViewModel = new ViewModelProvider(this).get(com.prideven.android.hungryeats.RestaurantViewModel.class);
        mViewModel.getRestaurantDetails().observe(getViewLifecycleOwner(), listOfRestaurants ->{
            ra.setData(listOfRestaurants);
            mViewModel.callRestaurantDataRepo(lat,lng);
        });
        mBinding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.callRestaurantDataRepo(lat,lng);
            }
        });
    }

    public int getID (int id){
        return id;
    }
}