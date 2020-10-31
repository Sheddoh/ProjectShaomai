package com.example.shaomai

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Customer (

    @Expose
    @SerializedName("customer_id") val customer_id :Int,
    @Expose
    @SerializedName("customer_name") val customer_name: String,
    @Expose
    @SerializedName("customer_tel") val customer_tel: Int,
    @Expose
    @SerializedName("customer_mail") val customer_mail : String,
    @Expose
    @SerializedName("customer_pass1") val customer_pass1: String,
    @Expose
    @SerializedName("customer_pass2") val customer_pass2: String){}