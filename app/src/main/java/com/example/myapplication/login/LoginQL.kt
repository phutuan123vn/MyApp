package com.example.myapplication.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.MYSQLHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.example.myapplication.quanly.QLnavigation
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.login3.*

class LoginQL : AppCompatActivity() {
    companion object{
        var user=User()
        var email:String=""
        var pass:String=""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login3)
        this.textchange(this)
        QLback.setOnClickListener {
            this.finish()
            overridePendingTransition(R.anim.no_animation, R.anim.slide_out_right)
        }
        val list= listOf<TextInputLayout>(QLUserfill,QLPassfill)
        val list1= listOf<TextInputEditText>(QLUser,QLPass)
        val listall=list.zip(list1)
        val load=LoadingDialog(this)
        QLbttn.setOnClickListener {
            hidekeyboard()
            if (email.isEmpty()|| pass.isEmpty()){
                for (i in listall){
                    if (i.second.text.toString().isEmpty()){
                        i.first.error="This can't not be empty"
                    }
                }
            }else{
                load.startLoading()
                val DBONL= MYSQLHandler(this)
                val handler= Handler()
                DBONL.getPassNRole(email,object : MYSQLHandler.VolleyCallback{
                    override fun onSuccess(Data: ArrayList<User>) {
                        super.onSuccess(Data)
                        if (Data.isEmpty()){
                            Toast.makeText(this@LoginQL,"Email or Password is Invalid",Toast.LENGTH_SHORT).show()
                        }else{
                            var passcheck=Data.get(0).Password
                            var role=Data.get(0).Role
                            if (pass==passcheck && role==2.toString()){
                                Toast.makeText(this@LoginQL,"Success Login",Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@LoginQL,QLnavigation::class.java))
                                handler.postDelayed(Runnable {
                                    load.isDissMiss()
                                    this@LoginQL.finish()
                                },5000L)
                            }else{
                                Toast.makeText(this@LoginQL,"Email or Password is Incorrect",Toast.LENGTH_SHORT).show()
                                load.dismissDialog()
                            }
                        }
                    }
                })
            }
        }

    }
    private fun textchange(context: Context) {
        QLUser.doAfterTextChanged {
            if (QLUser.text.toString().isEmpty()){
                QLUserfill.error = "Không được để trống"
            }else{
                email=QLUser.text.toString().trim().lowercase()
                QLUserfill.error=null
                QLUserfill.isErrorEnabled = false
            }
        }
        QLPass.doAfterTextChanged {
            if (QLPass.text.toString().isEmpty()){
                QLPassfill.error="Không được để trống"
            }else{
                pass=QLPass.text.toString().trim()
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