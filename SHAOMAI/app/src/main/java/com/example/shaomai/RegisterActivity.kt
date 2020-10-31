package com.example.shaomai

import CustomerAPI
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun goLog(view: View){
        var intent = Intent(this, LoginActivity::class.java).apply {
        }
        startActivity(intent)
    }


    fun signupto(v:View){
        val serv: CustomerAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CustomerAPI::class.java)

        serv.signup(
            input_name.text.toString(),
            input_tel.text.toString().toInt(),
            input_mail.text.toString(),
            input_pass1.text.toString(),
            input_pass2.text.toString()).enqueue(object :Callback<Customer>{
            override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                if(response.isSuccessful()){
                    Toast.makeText(applicationContext, "Samak Sed na", Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<Customer>, t: Throwable) {
                Toast.makeText(applicationContext, "Error onFailure " + t.message,Toast.LENGTH_LONG).show()
            }
        })







        }




}