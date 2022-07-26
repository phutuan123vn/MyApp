package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.GVnavigation
import com.example.myapplication.R
import com.example.myapplication.giangvien.GVnavigation
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
            val intent=Intent(this, SignUpGV::class.java)
            startActivity(intent)
        }
        GVforget.setOnClickListener{
            val intent=Intent(this, ForgetPass::class.java)
            startActivity(intent)
        }
        GVbttn.setOnClickListener {
            if (GVUser.text.toString().isEmpty()||GVPass.text.toString().isEmpty()){
                Toast.makeText(this,"Please Fill",Toast.LENGTH_SHORT).show()
            }else{
                var email=GVUser.text.toString()
                var pass=GVPass.text.toString()
                val db=DatabaseHandler(this)
                var ValueR=db.ViewPass(email)
                if (ValueR.isEmpty()){
                    Toast.makeText(this,"Password of Email Invalid",Toast.LENGTH_SHORT).show()
                }else{
                    var passcheck=ValueR.get(0).Password
                    var role=ValueR.get(0).Role
                    if ( pass == passcheck && role == 1.toString()){
                        Toast.makeText(this,"Success Login",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,GVnavigation::class.java))
                    }else{
                        Toast.makeText(this,"Password or Email is Incorrect",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}