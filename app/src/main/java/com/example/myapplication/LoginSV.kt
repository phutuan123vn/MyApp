package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.account.*
import kotlinx.android.synthetic.main.login1.*

class LoginSV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login1)
        SVback.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        SVsignup.setOnClickListener{
            val intent=Intent(this,SingUp::class.java)
            startActivity(intent)
        }
        SVforget.setOnClickListener{
            val intent=Intent(this,ForgetPass::class.java)
            startActivity(intent)
        }
    }
}