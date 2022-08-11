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
import com.example.myapplication.giangvien.GVnavigation
import com.example.myapplication.model.User
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.login2.*

class LoginGV : AppCompatActivity() {
    companion object{
        var email:String=""
        var pass:String=""
        var user=User()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login2)
        this.textchange(this)
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
        val db = DatabaseHandler(applicationContext)
        val list= listOf<TextInputLayout>(GVUserfill,GVPassfill)
        val list1= listOf<TextInputEditText>(GVUser,GVUser)
        val listall=list.zip(list1)
        val load=LoadingDialog(this)
        GVlog.setOnClickListener {
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
                            Toast.makeText(this@LoginGV,"Email or Password is Invalid",Toast.LENGTH_SHORT).show()
                        }else{
                            var passcheck=Data.get(0).Password
                            var role=Data.get(0).Role
                            if (pass==passcheck && role==1.toString()){
                                Toast.makeText(this@LoginGV,"Success Login",Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@LoginGV,GVnavigation::class.java))
                                handler.postDelayed(Runnable {
                                    load.isDissMiss()
                                    this@LoginGV.finish()
                                },5000L)
                            }else{
                                Toast.makeText(this@LoginGV,"Email or Password is Incorrect",Toast.LENGTH_SHORT).show()
                                load.dismissDialog()
                            }
                        }
                    }
                })
            }
        }

    }
    private fun textchange(context: Context) {
        GVUser.doAfterTextChanged {
            if (GVUser.text.toString().isEmpty()){
                GVUserfill.error = "Không được để trống"
            }else{
                email=GVUser.text.toString().trim().lowercase()
                GVUserfill.error=null
                GVUserfill.isErrorEnabled = false
            }
        }
        GVPass.doAfterTextChanged {
            if (GVPass.text.toString().isEmpty()){
                GVPassfill.error="Không được để trống"
            }else{
                pass=GVPass.text.toString().trim()
                GVPassfill.error=null
                GVPassfill.isErrorEnabled = false
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
        GVUser.clearFocus()
        GVPass.clearFocus()
    }
    fun closekeyboard(view: View) {
        hidekeyboard()
    }

}