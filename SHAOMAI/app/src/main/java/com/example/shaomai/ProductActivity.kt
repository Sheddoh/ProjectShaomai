package com.example.shaomai


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory


class ProductActivity : AppCompatActivity() {
    var productList = arrayListOf<Product>()
    val createClient = ProductAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        recycler_view.layoutManager = GridLayoutManager(this,2)
        recycler_view.addItemDecoration(
            DividerItemDecoration(recycler_view.getContext(),
            DividerItemDecoration.VERTICAL)
        )

    }
    override fun onResume() {
        super.onResume()
        callProductData()
    }
    fun callProductData(){
        productList.clear();
        val serv : ProductAPI = Retrofit.Builder() // Create Client
            .baseUrl("http://10.0.2.2:3000/") // Call PHP : http://10.0.2.2/movie_test/
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductAPI ::class.java)
        serv.retrieveProduct()
            .enqueue(object : Callback<List<Product>> {
                override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                    response.body()?.forEach {
                        productList.add(Product(it.product_id, it.product_name,it.product_price_rent,it.product_img)) }
//// Set Data to RecyclerRecyclerView
                    recycler_view.adapter = ProductAdapter(productList,applicationContext)
                    tv_product.text = "All Product : "+ productList.size.toString()+ " Product"
                }
                override fun onFailure(call: Call<List<Product>>, t: Throwable) = t.printStackTrace()
            }) }
    fun clickSearch(v:View){
        productList.clear()
        if(search_product.text.isEmpty()){
            callProductData()
        }else{
            createClient.retrieveProductID(search_product.text.toString())
                .enqueue(object :Callback<Product>{
                    override fun onResponse(call: Call<Product>, response: Response<Product>) {
                        if(response.isSuccessful){
                            productList.add(Product(response.body()?.product_id.toString().toInt(),
                            response.body()?.product_name.toString(),
                            response.body()?.product_price_rent.toString().toInt(),
                                response.body()?.product_img.toString()
                            ))
                            recycler_view.adapter = ProductAdapter(productList, applicationContext)

                        }else{
                            Toast.makeText(applicationContext, "Product is not Found", Toast.LENGTH_LONG).show()
                        }

                    }
                    override fun onFailure(call: Call<Product>, t: Throwable) {
                        Toast.makeText(applicationContext, "Eror onFailure", Toast.LENGTH_LONG).show()

                    }
                })
        }
    }
}
