package com.prideven.android.hungryeats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prideven.android.hungryeats.databinding.RestaurantListFileBinding
import java.util.*

class MainFragment : Fragment() {
    private var mViewModel: RestaurantViewModel? = null
    private var mBinding: RestaurantListFileBinding? =
        null
    private val rv: RecyclerView? = null
    var ra: RestaurantAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.restaurant_list_file, container, false)
        val rv = mBinding.restaurantList
        val items: List<EatsRestaurantsResponseItem> =
            ArrayList()
        ra = RestaurantAdapter(items)
        rv.adapter = ra
        rv.layoutManager = LinearLayoutManager(requireContext())
        return mBinding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel =
            ViewModelProvider(this).get(RestaurantViewModel::class.java)
        val zipCode: CharSequence = mBinding.searchZip.getQuery()
        //mViewModel.callRestaurantDataRepo(Integer.parseInt(zipCode.toString()));
        mViewModel!!.callRestaurantDataRepo(37.42274, -122.139956)
        mViewModel!!.restaurantDetails.observe(
            viewLifecycleOwner,
            Observer { listOfRestaurants: List<EatsRestaurantsResponseItem?>? ->
                ra!!.setData(
                    listOfRestaurants
                )
            }
        )

        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}