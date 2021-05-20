package com.prideven.android.hungryeats.menuitems

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */
class MenuItem {
    var item_name: String? = null
    var cal: String? = null
    var price: String? = null
    var image: String? = null
}