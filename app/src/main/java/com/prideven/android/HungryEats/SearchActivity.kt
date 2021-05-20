package com.prideven.android.hungryeats

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.prideven.android.hungryeats.RestaurantActivity

class SearchActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant_list)
        val mcd = findViewById<ImageView>(R.id.mcd)
        mcd.setOnClickListener { v ->
            startActivity(
                Intent(
                    v.context,
                    RestaurantActivity::class.java
                )
            )
        }
    }
}