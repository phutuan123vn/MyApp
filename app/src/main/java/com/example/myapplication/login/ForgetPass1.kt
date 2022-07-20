package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import kotlinx.android.synthetic.main.forgetpass1.*

class ForgetPass1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgetpass1)
        forget1reset.setOnClickListener {
            if(forget1code.text.toString().length>0){
                startActivity(Intent(this, ForgetPass3::class.java))
            }else{
                Toast.makeText(this,"Required Code",Toast.LENGTH_SHORT).show()
            }
        }
        forget1back.setOnClickListener {
            startActivity(Intent(this, ForgetPass::class.java))
        }
        forget1refresh.setOnClickListener {
            val intent=intent
            finish()
            startActivity(intent)
        }
    }
}