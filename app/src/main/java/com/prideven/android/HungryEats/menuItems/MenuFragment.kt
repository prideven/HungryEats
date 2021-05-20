package com.prideven.android.hungryeats.menuitems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.MenuAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.prideven.android.hungryeats.CartFirestore
import com.prideven.android.hungryeats.GetCartValuesListener
import com.prideven.android.hungryeats.R
import com.prideven.android.hungryeats.Root
import com.prideven.android.hungryeats.databinding.MenuFragmentBinding
import java.lang.reflect.Type

class MenuFragment : Fragment() {
    private var mViewModel: MenuViewModel? = null
    private var mBinding: MenuFragmentBinding? = null
    var ma: MenuAdapter? = null
    private val rv: RecyclerView? = null
    var id = 0
    var itemName: String? = null
    var itemPrice: String? = null
    var userId: String? = null
    private var db: FirebaseFirestore? = null
    private var restaurantName: String? = null
    private val eatsMenuResponse: MutableLiveData<EatsMenuResponse>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.menu_fragment, container, false)
        val rv = mBinding.menuItems
        db = FirebaseFirestore.getInstance()
        val ma = CustomAdapter(dataSet(),
            object : GetCartValuesListener {
                override fun onItemClick(
                    itemName: String?,
                    itemPrice: String?,
                    image: String?
                ) {
                    userId = FirebaseAuth.getInstance().currentUser.uid
                    addDataToFirestore(itemName, itemPrice, userId!!, image)
                    Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show()
                }
            }
        )
        rv.adapter = ma
        rv.layoutManager = LinearLayoutManager(requireContext())
        return mBinding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        // CharSequence zipCode = mBinding.searchZip.getQuery();
        //mViewModel.callRestaurantDataRepo(Integer.parseInt(zipCode.toString()));
        if (arguments != null) {
            id = arguments!!.getInt(RESTAURANT_ID, -1)
        }
        mViewModel.callMenuDataRepo(id)
        mViewModel.getMenuDetails().observe(viewLifecycleOwner, { listOfMenu ->
            mBinding!!.menuDescription.setText(listOfMenu.getDescription())
            mBinding!!.rating.text = "Rating: " + listOfMenu.getAverage_rating()
            restaurantName = listOfMenu.getName()
            mBinding!!.restaurntName.text = restaurantName
            activity!!.title = restaurantName
            Glide.with(mBinding!!.root.context)
                .load(listOfMenu.getCover_img_url())
                .centerCrop()
                .into(mBinding!!.mcdImg)
        })

        // TODO: Use the ViewModel
    }

    fun dataSet(): List<com.prideven.android.hungryeats.MenuItem>? {
        val gson = Gson()
        val root =
            gson.fromJson<Root>(
                menuItemData,
                Root::class.java as Type
            )
        return root.menu_item
    }

    private fun addDataToFirestore(
        itemName: String?,
        itemPrice: String?,
        userId: String,
        image: String?
    ) {

        // creating a collection reference
        // for our Firebase Firetore database.
        val dbCart = db!!.collection("CartDetails")

        // adding our data to our cart object class.
        val cartFirestore = CartFirestore(itemName, itemPrice, userId, image)

        // below method is use to add data to Firebase Firestore.
        dbCart.add(cartFirestore).addOnSuccessListener { }
            .addOnFailureListener { e -> // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(activity, "Fail to add item \n$e", Toast.LENGTH_SHORT).show()
            }
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
        const val RESTAURANT_ID = "restaurantIndex"
        fun newInstance(id: Int): MenuFragment {
            val bundle = Bundle()
            bundle.putInt(RESTAURANT_ID, id)
            val menuFragment = MenuFragment()
            menuFragment.arguments = bundle
            return menuFragment
        }

        var menuItemData = """{
  "menu_item": [

    {
      "item_name": "BBQ  CHEESEBURGER",
      "cal": "480cal",
      "price": "$18",
      "image": "https://images.prismic.io/hawaiianbarbecue/7fbbe5c9-a998-47a5-9476-19625d9900c5_Cheeseburger-optimized.png?auto=compress%2Cformat&w=1000&h=774"
    },

    {
      "item_name": "BBQ MIX",
      "cal": "420cal",
      "price": "$15",
      "image": "https://images.prismic.io/hawaiianbarbecue/05ad86c5-f251-4839-a4bd-0b2ec1bbcc55_BBQ_Mix_Short_Ribs-optimized.png?auto=compress%2Cformat&w=1000&h=775"
    },

    {
      "item_name": "FRIED SHRIMP",
      "cal": "380cal",
      "price": "$18",
      "image": "https://images.prismic.io/hawaiianbarbecue/25a8b8d0-78fa-4adc-980e-545f2b870a9f_Fried_Shrimp-optimized.png?auto=compress%2Cformat&w=1000&h=775"
    },

    {
      "item_name": "KALUA PORK",
      "cal": "620cal",
      "price": "$19",
      "image": "https://images.prismic.io/hawaiianbarbecue/02c2abca-f9d9-4b2c-8c3d-e3bd14271b28_Kalua_Pork_and_Cabbage-optimized.png?auto=compress%2Cformat&w=1000&h=775"
    },

    {
      "item_name": "LOCO MOCO",
      "cal": "420cal",
      "price": "$17",
      "image": "https://images.prismic.io/hawaiianbarbecue/8540b0dc-0f63-408c-bfba-eeb26399c374_Loco_Moco-optimized.png?auto=compress%2Cformat&w=1000&h=775"
    },

    {
      "item_name": "CHICKEN KATSU",
      "cal": "320cal",
      "price": "$12",
      "image": "https://images.prismic.io/hawaiianbarbecue/91ce129b-21cc-4c8f-b015-2f2eac96e875_Chicken_Katsu-optimized.png?auto=compress%2Cformat&w=1000&h=775"
    },

    {
      "item_name": "SEAFOOD COMBO",
      "cal": "610cal",
      "price": "$24",
      "image": "https://images.prismic.io/hawaiianbarbecue/0149c43f-1242-47da-83e5-ffce88d48b58_Seafood_Combo_BBQ_Chicken-optimized.png?auto=compress%2Cformat&w=1000&h=775"
    }
  ]
}

"""
    }
}