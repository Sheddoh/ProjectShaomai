package com.example.shaomai

import android.content.Intent
import android.os.Bundle
import android.os.SharedMemory.create
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import java.net.URI.create


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    fun nextpage(view: View) {
        val intent = Intent(this, LoginActivity::class.java).apply {

        }
        startActivity(intent)
    }






}