package com.prideven.android.hungryeats

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val orderButton = findViewById<Button>(R.id.btnOrder)
        orderButton.setOnClickListener { v ->
            startActivity(
                Intent(
                    v.context,
                    LoginAndRegisterActivity::class.java
                )
            )
        }
    }
}