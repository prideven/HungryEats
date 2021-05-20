package com.prideven.android.hungryeats

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.prideven.android.hungryeats.databinding.AddToCartBinding
import com.prideven.android.hungryeats.databinding.CartRvBinding
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment() {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    var addToCartBinding: AddToCartBinding? = null
    private var cartRV: RecyclerView? = null
    private var cartArrayList: ArrayList<CartFirestore?>? = null
    private var cartAdapter: CartAdapter? = null
    var db: FirebaseFirestore? = null
    var total = 0
    var `val` = 0
    var new_val = 0
    var cartRvBinding: CartRvBinding? = null

    // TODO: Rename and change types of parameters
    private val mParam1: String? = null
    var itemName: String? = null
    var price = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addToCartBinding = DataBindingUtil.inflate(inflater, R.layout.add_to_cart, container, false)
        cartRvBinding = DataBindingUtil.inflate(inflater, R.layout.cart_rv, container, false)
        cartRV = addToCartBinding.cartDetails
        db = FirebaseFirestore.getInstance()


        // creating our new array list
        cartArrayList = ArrayList()
        cartRV!!.setHasFixedSize(true)
        cartRV!!.layoutManager = LinearLayoutManager(requireContext())

        // adding our array list to our recycler view adapter class.

//        cartAdapter = new CartAdapter(cartArrayList);
        cartAdapter = CartAdapter(cartArrayList, CartListener { pos ->
            if (!cartArrayList!!.isEmpty() && pos < cartArrayList!!.size) {
                cartArrayList!!.removeAt(pos)
                findnewTotal(pos)
                cartAdapter!!.notifyDataSetChanged()
            }
            Toast.makeText(context, "Item deleted from cart", Toast.LENGTH_SHORT).show()
        })

        // setting adapter to our recycler view.
        cartRV!!.adapter = cartAdapter
        val userId = FirebaseAuth.getInstance().currentUser.uid
        db!!.collection("CartDetails").whereEqualTo("userId", userId).get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                // after getting the data we are calling on success method
                // and inside this method we are checking if the received
                // query snapshot is empty or not.
                if (!queryDocumentSnapshots.isEmpty) {
                    // if the snapshot is not empty we are
                    // hiding our progress bar and adding
                    // our data in a list.
                    val list =
                        queryDocumentSnapshots.documents
                    for (d in list) {                                // after getting this list we are passing
                        // that list to our object class.
                        val c =
                            d.toObject(CartFirestore::class.java)
                        c!!.id = d.id
                        // and we will pass this object class
                        // inside our arraylist which we have
                        // created for recycler view.
                        total += c.itemPrice!!.replace("$", "").toInt()
                        cartArrayList!!.add(c)
                    }
                    // after adding the data to recycler view.
                    // we are calling recycler view notifuDataSetChanged
                    // method to notify that data has been changed in recycler view.
                    cartAdapter!!.notifyDataSetChanged()
                    addToCartBinding.tPrice.text = "$$total"
                } else {
                    // if the snapshot is empty we are displaying a toast message.
                    Toast.makeText(context, "No data found in cart", Toast.LENGTH_SHORT)
                        .show()
                }
            }.addOnFailureListener { // if we do not get any data or any error we are displaying
                // a toast message that we do not get any data
                Toast.makeText(context, "Fail to get the data.", Toast.LENGTH_SHORT).show()
            }
        addToCartBinding.checkout.setOnClickListener {
            Toast.makeText(context, "Order is placed successfully", Toast.LENGTH_SHORT)
                .show()
            val fragmentManager =
                Objects.requireNonNull(activity).supportFragmentManager
            val fragmentTransaction =
                fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, RestaurantFragment.newInstance())
            fragmentTransaction.commit()
        }
        return addToCartBinding.getRoot()
    }

    private fun deleteItem(pos: Int) {

        // below line is for getting the collection
        // where we are storing our courses.
        db!!.collection("CartDetails").document // after that we are getting the document
        // which we have to delete.
        itemName.delete // after passing the document id we are calling
        // delete method to delete this document.
        ().addOnCompleteListener // after deleting call on complete listener
        // method to delete this data.
        OnCompleteListener<Void?> { task ->
            // inside on complete method we are checking
            // if the task is success or not.
            if (task.isSuccessful) {
                // this method is called when the task is success
                // after deleting we are starting our MainActivity.
                Toast.makeText(
                    context,
                    "Item has been deleted from cart.",
                    Toast.LENGTH_SHORT
                ).show()
                val i = Intent(context, CartFragment::class.java)
                startActivity(i)
            } else {
                // if the delete operation is failed
                // we are displaying a toast message.
                Toast.makeText(context, "Fail to delete the item. ", Toast.LENGTH_SHORT)
                    .show()
            }
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

    fun findnewTotal(pos: Int) {
        new_val = 0
        for (i in cartArrayList!!.indices) {
            val val1 = cartArrayList!![i]!!.itemPrice
            new_val += val1!!.replace("$", "").toInt()
        }
        addToCartBinding!!.tPrice.text = "$$new_val"
    }

    companion object {
        const val ITEM_NAME = "itemName"
        const val ITEM_PRICE = "itemPrice"

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(): CartFragment {
            val fragment = CartFragment()
            val bundle = Bundle()
            val cartFragment = CartFragment()
            cartFragment.arguments = bundle
            return cartFragment
        }
    }
}