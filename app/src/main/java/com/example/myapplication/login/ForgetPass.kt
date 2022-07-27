package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import kotlinx.android.synthetic.main.forgetpass.*

class ForgetPass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgetpass)
        forgetsend.setOnClickListener {
            if (forgetemail.text.toString().length>0){
                startActivity(Intent(this, ForgetPass1::class.java))
            }else{
                Toast.makeText(this,"Please Fill Your Email",Toast.LENGTH_SHORT).show()
            }
        }
        forgetback.setOnClickListener { this.finish() }
    }
}