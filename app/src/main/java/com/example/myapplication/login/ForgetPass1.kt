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
import kotlinx.android.synthetic.main.forgetpass1.*

class ForgetPass1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgetpass1)
        forget1reset.setOnClickListener {
            hidekeyboard()
            if(forget1code.text.toString().length>0){
                startActivity(Intent(this, ForgetPass2::class.java))
                this.finish()
            }else{
                Toast.makeText(this,"Required Code",Toast.LENGTH_SHORT).show()
            }
        }
        forget1back.setOnClickListener {
            this.finish()
        }
        forget1refresh.setOnClickListener {
            val intent=intent
            finish()
            startActivity(intent)
        }
    }
    fun hidekeyboard() {
        val xem = this.currentFocus
        if (xem != null) {
            val hide = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hide.hideSoftInputFromWindow(xem.windowToken, 0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        forget1code.clearFocus()
    }
    fun closekeyboard(view: View) {
        hidekeyboard()
    }
}