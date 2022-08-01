package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.DATABASE_NAME
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.example.myapplication.quanly.QLnavigation
import kotlinx.android.synthetic.main.login1.*
import kotlinx.android.synthetic.main.login3.*

class LoginQL : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login3)
        QLback.setOnClickListener {
            this.finish()
        }
        QLbttn.setOnClickListener {
            if (QLUser.text.toString().isNotEmpty() && QLPass.text.toString().isNotEmpty()){
                var email=QLUser.text.toString()
                var pass=QLPass.text.toString()
                val db= DatabaseHandler(this)
                var ValueR=db.ViewPass(email)
                if (ValueR.isEmpty()){
                    Toast.makeText(this,"Email or Password is Invalid",Toast.LENGTH_SHORT).show()
                }else{
                    var passcheck=ValueR.get(0).Password
                    var role=ValueR.get(0).Role
                    if (pass == passcheck && role == 2.toString()){
                        Toast.makeText(this,"Success Login",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,QLnavigation::class.java))
                        this.finish()
                    }else{
                        Toast.makeText(this,"Password or Email is Incorrect",Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                if (QLUser.text.toString().isEmpty()) {
                    QLUserfill.error = "Không được để trống"}
                else{
                    QLUserfill.error = null
                }
                if (QLPass.text.toString().isEmpty()){
                    QLPassfill.error = "Không được để trống"
                } else {
                    QLPassfill.error = null}
            }
        }

    }
}