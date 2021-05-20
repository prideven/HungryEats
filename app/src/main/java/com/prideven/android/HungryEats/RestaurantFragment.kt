package com.prideven.android.hungryeats

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prideven.android.hungryeats.databinding.RestaurantLayoutBinding
import com.prideven.android.hungryeats.databinding.RestaurantListFileBinding
import com.prideven.android.hungryeats.menuitems.MenuFragment
import java.io.IOException
import java.util.*

class RestaurantFragment : Fragment() {
    private var mViewModel: RestaurantViewModel? = null
    private var mBinding: RestaurantListFileBinding? =
        null
    private val rBinding: RestaurantLayoutBinding? =
        null
    private val rv: RecyclerView? = null
    var ra: RestaurantAdapter? = null
    private var lat: Double? = null
    private var lng: Double? = null
    val zip = "95192"
    var id = 0
    var message: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.restaurant_list_file, container, false)
        val rv = mBinding.restaurantList
        val items: List<EatsRestaurantsResponseItem> =
            ArrayList()
        ra = RestaurantAdapter(items,
            GetRestaurantIDListener { Id ->
                val menuFragment: MenuFragment = MenuFragment.newInstance(Id)
                val fragmentManager =
                    Objects.requireNonNull(activity).supportFragmentManager
                val fragmentTransaction =
                    fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container, menuFragment)
                fragmentTransaction.addToBackStack(RestaurantFragment::class.java.name)
                fragmentTransaction.commit()
            })
        rv.adapter = ra
        rv.layoutManager = LinearLayoutManager(requireContext())
        return mBinding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        val geocoder = Geocoder(context)
        try {
            val addresses =
                geocoder.getFromLocationName(zip, 1)
            if (addresses != null && !addresses.isEmpty()) {
                val address = addresses[0]
                // Use the address as needed
                val message = String.format(
                    "Latitude: %f, Longitude: %f",
                    address.latitude, address.longitude
                )
                lat = address.latitude
                lng = address.longitude
            } else {
                // Display appropriate message when Geocoder services are not available
                Toast.makeText(context, "Unable to geocode zipcode", Toast.LENGTH_LONG).show()
            }
        } catch (e: IOException) {
            // handle exception
        }
        mViewModel =
            ViewModelProvider(this).get(RestaurantViewModel::class.java)
        mViewModel!!.restaurantDetails.observe(
            viewLifecycleOwner,
            Observer { listOfRestaurants: List<EatsRestaurantsResponseItem?>? ->
                ra!!.setData(
                    listOfRestaurants
                )
            }
        )
        mBinding!!.search.setOnClickListener { mViewModel!!.callRestaurantDataRepo(lat!!, lng!!) }
    }

    fun getID(id: Int): Int {
        return id
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity!!.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance(): RestaurantFragment {
            return RestaurantFragment()
        }
    }
}