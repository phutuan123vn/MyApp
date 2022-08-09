package com.example.myapplication.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.quanly.QLnavigation
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.login3.*

class LoginQL : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login3)
        this.textchange(this)
        QLback.setOnClickListener {
            this.finish()
        }
        val list= listOf<TextInputLayout>(QLUserfill,QLPassfill)
        val list1= listOf<TextInputEditText>(QLUser,QLPass)
        val listall=list.zip(list1)
        QLbttn.setOnClickListener {
            hidekeyboard()
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
                        startActivity(Intent(this, QLnavigation::class.java))
                        this.finish()
                    }else{
                        Toast.makeText(this,"Password or Email is Incorrect",Toast.LENGTH_SHORT).show()
                    }
                }
            }else {
                for (i in listall) {
                    if (i.second.text.toString().isEmpty()) {
                        i.first.error = "Không được để trống"
                    }
                }
            }
        }

    }
    private fun textchange(context: Context) {
        QLUser.doAfterTextChanged {
            if (QLUser.text.toString().isEmpty()){
                QLUserfill.error = "Không được để trống"
            }else{
                QLUserfill.error=null
                QLUserfill.isErrorEnabled = false
            }
        }
        QLPass.doAfterTextChanged {
            if (QLPass.text.toString().isEmpty()){
                QLPassfill.error="Không được để trống"
            }else{
                QLPassfill.error=null
                QLPassfill.isErrorEnabled = false
            }
        }
    }
    fun hidekeyboard() {
        val xem = this.currentFocus
        if (xem != null) {
            val hide = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hide.hideSoftInputFromWindow(xem.windowToken, 0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        QLUser.clearFocus()
        QLPass.clearFocus()
    }
    fun closekeyboard(view: View) {
        hidekeyboard()
    }

}