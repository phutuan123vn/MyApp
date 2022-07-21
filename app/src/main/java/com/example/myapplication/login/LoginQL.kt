package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.login3.*

class LoginQL : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login3)
        QLback.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}