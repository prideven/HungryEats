package com.prideven.android.hungryeats;


public class CartFirestore {

    // variables for storing our data.
    public String itemName, itemPrice, userId;

    public CartFirestore() {
        // empty constructor
        // required for Firebase.
    }

    // Constructor for all variables.
    public CartFirestore(String itemName, String itemPrice, String userId) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.userId = userId;
    }

    // getter methods for all variables.
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    // setter method for all variables.
    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}