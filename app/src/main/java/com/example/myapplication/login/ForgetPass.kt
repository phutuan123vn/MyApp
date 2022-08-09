package com.example.myapplication.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.myapplication.R
import kotlinx.android.synthetic.main.forgetpass.*
import kotlinx.android.synthetic.main.login1.*

class ForgetPass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgetpass)
        forgetsend.setOnClickListener {
            hidekeyboard()
            if (forgetemail.text.toString().length>0){
                startActivity(Intent(this, ForgetPass1::class.java))
            }else{
                Toast.makeText(this,"Please Fill Your Email",Toast.LENGTH_SHORT).show()
            }
        }
        forgetback.setOnClickListener { this.finish() }
    }

    fun hidekeyboard() {
        val xem = this.currentFocus
        if (xem != null) {
            val hide = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hide.hideSoftInputFromWindow(xem.windowToken, 0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        forgetemail.clearFocus()
    }
    fun closekeyboard(view: View) {
        hidekeyboard()
    }
}