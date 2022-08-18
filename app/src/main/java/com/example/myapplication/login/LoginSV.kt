package com.example.myapplication.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.doAfterTextChanged
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.MYSQLHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.example.myapplication.sinhvien.SVnavigation
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.login1.*


class LoginSV : AppCompatActivity() {
    companion object{
        var user= User()
        var email :String=""
        var pass :String=""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.login1)
        this.textchange(this)
        SVback.setOnClickListener {
            this.finish()
            overridePendingTransition(R.anim.no_animation, R.anim.slide_out_right)
        }
        SVsignup.setOnClickListener {
            val intent = Intent(this, SignUpSV::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        SVforget.setOnClickListener {
            val intent = Intent(this, ForgetPass::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        val db = DatabaseHandler(applicationContext)
        val list= listOf<TextInputLayout>(L1userfill,L1passfill)
        val list1= listOf<TextInputEditText>(L1user,L1pass)
        val listall=list.zip(list1)
        val load=LoadingDialog(this)
        L1log.setOnClickListener {
            hidekeyboard()
            if (email.isEmpty()|| pass.isEmpty()){
                for (i in listall){
                    if (i.second.text.toString().isEmpty()){
                        i.first.error="This can't not be empty"
                    }
                }
            }else{
                load.startLoading()
                val DBONL=MYSQLHandler(this)
                val handler=Handler()
                DBONL.getPassNRole(email,object : MYSQLHandler.VolleyCallback{
                    override fun onSuccess(Data: ArrayList<User>) {
                        super.onSuccess(Data)
                        if (Data.isEmpty()){
                            Toast.makeText(this@LoginSV,"Email or Password is Invalid",Toast.LENGTH_SHORT).show()
                        }else{
                            var passcheck=Data.get(0).Password
                            var role=Data.get(0).Role
                            if (pass==passcheck && role==0.toString()){
                                Toast.makeText(this@LoginSV,"Success Login",Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@LoginSV,SVnavigation::class.java))
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                handler.postDelayed(Runnable {
                                    load.isDissMiss()
                                    this@LoginSV.finish()
                                },5000L)
                            }else{
                                Toast.makeText(this@LoginSV,"Email or Password is Incorrect",Toast.LENGTH_SHORT).show()
                                load.dismissDialog()
                            }
                        }
                    }
                })
            }
        }
    }

    private fun textchange(context: Context) {
        L1user.doAfterTextChanged {
            if (L1user.text.toString().isEmpty()) {
                L1userfill.error = "This can't be Empty"
            } else {
                email=L1user.text.toString().trim().lowercase()
                L1userfill.error = null
                L1userfill.isErrorEnabled = false
            }
        }
        L1pass.doAfterTextChanged {
            if (L1pass.text.toString().isEmpty()) {
                L1passfill.error = "This can't be Empty"
            } else {
                pass=L1pass.text.toString().trim()
                L1passfill.error = null
                L1passfill.isErrorEnabled = false
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
        L1user.clearFocus()
        L1pass.clearFocus()
    }
    fun closekeyboard(view: View) {
        hidekeyboard()
    }

}

//private fun Handler.postDelayed(runnable: Runnable){
//    val handler=Handler()
//    handler.postDelayed(object : Runnable{
//        override fun run() {
//
//        }
//    })
//}
