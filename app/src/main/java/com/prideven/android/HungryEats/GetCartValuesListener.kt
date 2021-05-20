package com.prideven.android.hungryeats

interface GetCartValuesListener {
    fun onItemClick(
        itemName: String?,
        price: String?,
        image: String?
    )
}