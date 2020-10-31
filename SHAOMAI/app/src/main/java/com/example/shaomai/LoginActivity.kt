package com.example.shaomai

import CustomerAPI
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    var userList  = arrayListOf<Customer>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }
    fun ContactDetail(v: View) {
        val builder = AlertDialog.Builder(this)
        val negativeButtonClick = { dialog: DialogInterface, which: Int ->
            Toast.makeText(applicationContext, "Close Contact", Toast.LENGTH_SHORT).show()
        }
        builder.setTitle("Sign Up")
        builder.setMessage("Please contact Developer \nBY ZENZOMARU")
        builder.setNegativeButton("Close", DialogInterface.OnClickListener(function = negativeButtonClick))
        builder.show()
    }

    fun Login(v: View){
        val intent = Intent(this,ProductActivity::class.java)
        userList.clear()
        val api : CustomerAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CustomerAPI :: class.java)

        api.logincus(
            input_email.text.toString(),
            input_password.text.toString()).enqueue(object : Callback<Customer>{
            override fun onResponse(call: Call<Customer>,response: Response<Customer>){
                userList.add(
                    Customer(response.body()?.customer_id.toString().toInt(),
                    response.body()?.customer_name.toString(),
                    response.body()?.customer_tel.toString().toInt(),
                    response.body()?.customer_mail.toString(),
                    response.body()?.customer_pass1.toString(),
                    response.body()?.customer_pass2.toString()
                )
                )
                if (response.isSuccessful()){
                    Toast.makeText(applicationContext,"Login Successfull !!",Toast.LENGTH_SHORT).show()
                    intent.putExtra("mUid",response.body()?.customer_id.toString())
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext,"error !",Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Customer>,t: Throwable){
                Toast.makeText(applicationContext,"Incorrect !"+t.message,Toast.LENGTH_LONG).show()
            }
        })

    }


    fun goSign(v:View){
        val sign = Intent(this,RegisterActivity::class.java)
        startActivity(sign)
    }


}

