package com.prideven.android.hungryeats;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.prideven.android.hungryeats.menuitems.MenuFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MenuFragment.newInstance())
                    .commitNow();
        }
    }
}