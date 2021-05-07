package com.prideven.android.hungryeats;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;


public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_fragment);
//        GetCartValuesListener listener = null;
//
//        RecyclerView rv = findViewById(R.id.menu_items);
//        CustomAdapter ca = new CustomAdapter(dataSet(),GetCartValuesListener listener);
//        rv.setAdapter(ca);
//        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    public List<com.prideven.android.hungryeats.MenuItem> dataSet() {
        Gson gson = new Gson();

        com.prideven.android.hungryeats.Root root = gson.fromJson(menuItemData, (Type) com.prideven.android.hungryeats.Root.class);
        return root.menu_item;
    }

    static String menuItemData = "{\n" +
            "  \"menu_item\": [\n" +
            "\n" +
            "    {\n" +
            "      \"item_name\": \"French Fries\",\n" +
            "      \"cal\": \"220cal\",\n" +
            "      \"price\": \"$8\",\n" +
            "      \"image\": \"https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"item_name\": \"Veg Burger\",\n" +
            "      \"cal\": \"420cal\",\n" +
            "      \"price\": \"$15\",\n" +
            "      \"image\": \"https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"item_name\": \"Chicken Burger\",\n" +
            "      \"cal\": \"520cal\",\n" +
            "      \"price\": \"$18\",\n" +
            "      \"image\": \"https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"item_name\": \"Nuggets\",\n" +
            "      \"cal\": \"920cal\",\n" +
            "      \"price\": \"$19\",\n" +
            "      \"image\": \"https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"item_name\": \"Coke\",\n" +
            "      \"cal\": \"220cal\",\n" +
            "      \"price\": \"$8\",\n" +
            "      \"image\": \"https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"item_name\": \"Egg Burger\",\n" +
            "      \"cal\": \"320cal\",\n" +
            "      \"price\": \"$12\",\n" +
            "      \"image\": \"https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg\"\n" +
            "    },\n" +
            "\n" +
            "    {\n" +
            "      \"item_name\": \"Meals\",\n" +
            "      \"cal\": \"920cal\",\n" +
            "      \"price\": \"$22\",\n" +
            "      \"image\": \"https://www.rd.com/wp-content/uploads/2018/10/shutterstock_1033217323-scaled.jpg\"\n" +
            "    }\n" +
            "  ]\n" +
            "}\n" +
            "\n";
}

