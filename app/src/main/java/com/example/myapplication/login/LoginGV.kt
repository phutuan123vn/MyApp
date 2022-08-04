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
import com.example.myapplication.giangvien.GVnavigation
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
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
        val db = DatabaseHandler(applicationContext)
        val list= listOf<TextInputLayout>(GVUserfill,GVPassfill)
        val list1= listOf<TextInputEditText>(GVUser,GVUser)
        val listall=list.zip(list1)
        GVlog.setOnClickListener {
            hidekeyboard()
            if (GVUser.text.toString().isNotEmpty() && GVPass.text.toString().isNotEmpty()){
                var pass = GVUser.text.toString()
                var email = GVUser.text.toString()
                var ValueR = db.ViewPass(email)
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
            }else{
                for (i in listall) {
                    if (i.second.text.toString().isEmpty()) {
                        i.first.error = "Không được để trống"
                    }
                }
            }
        }

    }
    private fun textchange(context: Context) {
        GVUser.doAfterTextChanged {
            if (GVUser.text.toString().isEmpty()){
                GVUserfill.error = "Không được để trống"
            }else{
                GVUserfill.error=null
                GVUserfill.isErrorEnabled = false
            }
        }
        GVPass.doAfterTextChanged {
            if (GVPass.text.toString().isEmpty()){
                GVPassfill.error="Không được để trống"
            }else{
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