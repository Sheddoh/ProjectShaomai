package com.example.shaomai

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product (
    @Expose
    @SerializedName("product_id") val product_id: Int,

    @Expose
    @SerializedName("product_name") val product_name: String,

    @Expose
    @SerializedName("product_price_rent") val product_price_rent: Int,

    @Expose
    @SerializedName("product_img") val product_img: String){}
