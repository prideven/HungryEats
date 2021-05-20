package com.prideven.android.hungryeats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.lang.reflect.Type

class RestaurantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_fragment)
    }

    fun dataSet(): List<MenuItem> {
        val gson = Gson()
        val root =
            gson.fromJson<Root>(
                menuItemData,
                Root::class.java as Type
            )
        return root.menu_item
    }

    companion object {
        var menuItemData = """{
  "menu_item": [

    {
      "item_name": "French Fries",
      "cal": "220cal",
      "price": "$8",
      "image": "https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg"
    },

    {
      "item_name": "Veg Burger",
      "cal": "420cal",
      "price": "$15",
      "image": "https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg"
    },

    {
      "item_name": "Chicken Burger",
      "cal": "520cal",
      "price": "$18",
      "image": "https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg"
    },

    {
      "item_name": "Nuggets",
      "cal": "920cal",
      "price": "$19",
      "image": "https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg"
    },

    {
      "item_name": "Coke",
      "cal": "220cal",
      "price": "$8",
      "image": "https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg"
    },

    {
      "item_name": "Egg Burger",
      "cal": "320cal",
      "price": "$12",
      "image": "https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg"
    },

    {
      "item_name": "Meals",
      "cal": "920cal",
      "price": "$22",
      "image": "https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg"
    }
  ]
}

"""
    }
}