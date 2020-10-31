package com.example.shaomai


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPI {
    @GET("allproduct") /// Call NodeJS
// @GET("allmovie.php") // Call PHP file
    fun retrieveProduct(): Call<List<Product>>

    @GET("product/{product_name}")
    fun retrieveProductID(
        @Path("product_name") product_name: String
    ): Call<Product>

    companion object{
        fun create(): ProductAPI{
            val dormClient : ProductAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductAPI ::class.java)
            return dormClient
        }
    }

}