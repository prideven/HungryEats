package com.prideven.android.hungryeats.menuitems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prideven.android.hungryeats.R;
import com.prideven.android.hungryeats.databinding.MenuItemsBinding;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

    private MenuViewModel mViewModel;
    public MenuItemsBinding mBinding;
    public MenuAdapter ma;
    private RecyclerView rv;
    public int id=30;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.menu_items, container, false);
        RecyclerView rv = mBinding.menuItems;
        List<EatsMenuResponse> items = new ArrayList<>();
        ma = new com.prideven.android.hungryeats.menuitems.MenuAdapter(items);
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
            ma.setData(listOfMenu);
            // Update the UI.
        });




        // TODO: Use the ViewModel
    }

}