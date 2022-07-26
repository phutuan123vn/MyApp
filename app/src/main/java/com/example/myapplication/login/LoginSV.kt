package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.sinhvien.SVnavigation
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
            val intent=Intent(this, SignUpSV::class.java)
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
                var pass=L1pass.text.toString()
                var email=L1user.text.toString()
                var ValueR=db.ViewPass(email)
                if (ValueR.isEmpty()){
                    Toast.makeText(this,"Password of Email Invalid",Toast.LENGTH_SHORT).show()
                }else{
                    var passcheck=ValueR.get(0).Password
                    var role=ValueR.get(0).Role
                    if ( pass == passcheck && role == 0.toString() ){
                        Toast.makeText(this,"Success Login",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,SVnavigation::class.java))
                    }else{
                        Toast.makeText(this,"Password or Email is Incorrect",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }




    }
}