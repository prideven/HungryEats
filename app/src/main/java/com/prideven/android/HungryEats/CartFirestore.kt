package com.prideven.android.hungryeats

class CartFirestore {
    // getter methods for all variables.
    // variables for storing our data.
    var itemName: String? = null

    // setter method for all variables.
    var itemPrice: String? = null
    var userId: String? = null
    var image: String? = null
    var id: String? = null

    constructor() {
        // empty constructor
        // required for Firebase.
    }

    // Constructor for all variables.
    constructor(
        itemName: String?,
        itemPrice: String?,
        userId: String?,
        image: String?
    ) {
        this.itemName = itemName
        this.itemPrice = itemPrice
        this.userId = userId
        this.image = image
    }

}