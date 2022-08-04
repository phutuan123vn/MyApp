package com.example.myapplication.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.example.myapplication.R
import com.example.myapplication.sinhvien.SVnavigation
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.login1.*


class LoginSV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        val list= listOf<TextInputLayout>(L1userfill,L1passfill)
        val list1= listOf<TextInputEditText>(L1user,L1pass)
        val listall=list.zip(list1)
        L1log.setOnClickListener {
            hidekeyboard()
            if (L1user.text.toString().isNotEmpty() && L1pass.text.toString().isNotEmpty()) {
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
            } else {
                for (i in listall){
                    if (i.second.text.toString().isEmpty()){
                        i.first.error="Không được để trống"
                    }
                }
            }
        }
    }

    private fun textchange(context: Context) {
        L1user.doAfterTextChanged {
            if (L1user.text.toString().isEmpty()) {
                L1userfill.error = "Không được để trống"
            } else {
                L1userfill.error = null
                L1userfill.isErrorEnabled = false
            }
        }
        L1pass.doAfterTextChanged {
            if (L1pass.text.toString().isEmpty()) {
                L1passfill.error = "Không được để trống"
            } else {
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
