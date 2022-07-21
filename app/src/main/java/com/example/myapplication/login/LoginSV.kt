package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.SVnavigation
import kotlinx.android.synthetic.main.login1.*

class LoginSV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login1)
        SVback.setOnClickListener {
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        SVsignup.setOnClickListener{
            val intent=Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        SVforget.setOnClickListener{
            val intent=Intent(this, ForgetPass::class.java)
            startActivity(intent)
        }
        val db= DatabaseHandler(applicationContext)
        L1log.setOnClickListener {
            if (L1user.text.toString().isEmpty() || L1pass.text.toString().isEmpty()){
                Toast.makeText(this, "Please FIll", Toast.LENGTH_SHORT).show()
            }else {
//            db.CheckUser(L1user.text.toString(),L1pass.text.toString())
                if (db.CheckUser(L1user.text.toString(),L1pass.text.toString())){
                    Toast.makeText(this, "Succes Log In", Toast.LENGTH_SHORT).show()
                    val intent=Intent(this, SVnavigation::class.java)
                    startActivity(intent)
                }
            }
        }




    }
}