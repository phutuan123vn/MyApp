package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.login2.*

class LoginGV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login2)
        GVback.setOnClickListener {
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        GVsignup.setOnClickListener{
            val intent=Intent(this, SingUp::class.java)
            startActivity(intent)
        }
        GVforget.setOnClickListener{
            val intent=Intent(this, ForgetPass::class.java)
            startActivity(intent)
        }

    }
}