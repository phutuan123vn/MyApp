package com.example.myapplication.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
//import com.example.myapplication.databinding.Login1Binding
import com.example.myapplication.sinhvien.SVnavigation
import kotlinx.android.synthetic.main.gv_qltkdetail.*
import kotlinx.android.synthetic.main.login1.*
import kotlinx.android.synthetic.main.login2.view.*

class LoginSV : AppCompatActivity() {
    //private lateinit var  binding: Login1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = Login1Binding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(R.layout.login1)
        this.textchange(this)
        SVback.setOnClickListener {
            this.finish()
        }
        SVsignup.setOnClickListener {
            val intent = Intent(this, SignUpSV::class.java)
            startActivity(intent)
        }
        SVforget.setOnClickListener {
            val intent = Intent(this, ForgetPass::class.java)
            startActivity(intent)
        }
        val db = DatabaseHandler(applicationContext)
        L1log.setOnClickListener {
            if (L1user.text.toString().isNotEmpty() && L1pass.text.toString().isNotEmpty()){
                var pass = L1pass.text.toString()
                var email = L1user.text.toString()
                var ValueR = db.ViewPass(email)
                if (ValueR.isEmpty()) {
                    Toast.makeText(this, "Password of Email Invalid", Toast.LENGTH_SHORT).show()
                } else {
                    var passcheck = ValueR.get(0).Password
                    var role = ValueR.get(0).Role
                    if (pass == passcheck && role == 0.toString()) {
                        Toast.makeText(this, "Success Login", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, SVnavigation::class.java))
                        this.finish()
                    } else {
                        Toast.makeText(this, "Password or Email is Incorrect", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else{
                L1userfill.error="Không được để trống"
                L1passfill.error="Không được để trống"
            }
        }
    }

    private fun textchange(context: Context) {
        L1user.doAfterTextChanged {
            if (L1user.text.toString().isEmpty()){
                L1userfill.error = "Không được để trống"
            }else{
                L1userfill.error=null
                L1userfill.isErrorEnabled = false
            }
        }
        L1pass.doAfterTextChanged {
            if (L1pass.text.toString().isEmpty()){
                L1passfill.error="Không được để trống"
            }else{
                L1passfill.error=null
                L1passfill.isErrorEnabled = false
            }
        }
    }
}