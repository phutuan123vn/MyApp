package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.giangvien.GVnavigation
import kotlinx.android.synthetic.main.login1.*
import kotlinx.android.synthetic.main.login2.*

class LoginGV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login2)
        GVback.setOnClickListener {
            this.finish()
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
            if (GVUser.text.toString().isEmpty()) {
                GVUserfill.error = "Không được để trống"}
            else{
                GVUserfill.error = null
            }
            if (GVPass.text.toString().isEmpty()){
                GVPassfill.error = "Không được để trống"
            } else {
                GVPassfill.error = null}

            if (GVUser.text.toString().isNotEmpty() && GVPass.text.toString().isNotEmpty()){
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
                        this.finish()
                    }else{
                        Toast.makeText(this,"Password or Email is Incorrect",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}