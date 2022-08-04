package com.example.myapplication.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.login1.*
import kotlinx.android.synthetic.main.signupgv.*
import kotlinx.android.synthetic.main.signupsv.*
import java.util.regex.Pattern

class SignUpGV : AppCompatActivity() {
    companion object{
        var al8 : Boolean = false
        var uc : Boolean = false
        var num : Boolean = false
        var pass : String =""
        var passcon: String=""
        var email :String=""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signupgv)
        val db=DatabaseHandler(this)
        pass=SUGPass.text.toString()
        passcon=SUGPassCon.text.toString()
        this.onTextChange(this)
        SUGE.doAfterTextChanged { email=SUGE.text.toString() }
        SUGback.setOnClickListener { this.finish() }
        SUGbttn.setOnClickListener{
            hidekeyboard()
            var emailvalid :Boolean = false
            var passmatch :Boolean =false
            if (checkInputEmpty(this)){
                Toast.makeText(this,"Fill Please",Toast.LENGTH_SHORT).show()
            }else {
                //check input
                if (al8 && uc && num){
                    SUGPassHT.helperText=null
                    SUGPassConHT.helperText=null
                }
                //Pass khong thoa dieu kien
                else {
                    SUGPassHT.helperText="Password Must be Correct"
                    SUGPassConHT.helperText="Password Must be Correct"
                }
                if (isEmailValid(email)){
                    SUGEHT.helperText=null
                    val mailcheck=db.checkmail(email)
                    if (email != mailcheck){
                        SUGEHT.helperText=null
                        emailvalid = true
                    }else{
                        SUGEHT.helperText="Email have been used"
                    }
                }else{
                    SUGEHT.helperText="Email is Invalid"
                }
                if (pass == passcon){
                    SUGPassHT.helperText=null
                    SUGPassConHT.helperText=null
                    passmatch=true
                }else{
                    SUGPassHT.helperText="Password not match"
                    SUGPassConHT.helperText="Password not match"
                }
                if (al8 && uc && num && emailvalid && passmatch){
                    var user=User(
                        SUGLname.text.toString(),
                        SUGFname.text.toString(),
                        1.toString(),
                        email, pass
                    )
                    db.insertData(user)
                    this.finish()
                }
            }
        }
    }

    private fun onTextChange(context: Context) {
        SUGPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //


            }

            override fun afterTextChanged(s: Editable?) {
                //
                pass=SUGPass.text.toString()
                passwordValidate(pass, passcon)

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //

            }
        })
        SUGPassCon.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //
                passcon=SUGPassCon.text.toString()
                passwordValidate(pass, passcon)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

    }


    //kiem tra email chua xong
    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    // kiem tra email hcmut
    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))"
                    + "@gmail.com$"  //thay doi email tai day
        ).matcher(email).matches()
    }
    // ban? nhap' kiem tra PASSWORD
    public fun ValidPasswordFormat(password: String) {
        val passwordREGEX = Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$");

    }
    // Pass world validate da xong
    private fun passwordValidate(pass:String, passcon:String) {
        if (pass.length>=8 && passcon.length>=8 ){
            GVBox1.isChecked=true
            al8=true
        }else{
            al8=false
            GVBox1.isChecked=false
        }
        if(pass.matches((".*[A-Z].*").toRegex()) && passcon.matches((".*[A-Z].*").toRegex()) ){
            uc=true
            GVBox3.isChecked=true
        }else{
            uc=false
            GVBox3.isChecked=false
        }
        if (pass.matches((".*[0-9].*").toRegex()) && passcon.matches((".*[0-9].*").toRegex()) ){
            num=true
            GVBox2.isChecked=true
        }else{
            num=false
            GVBox2.isChecked=false
        }
    }
    // KIEM TRA INPUT da xong
    private fun checkInputEmpty(context: Context):Boolean{
        val list=listOf<TextInputLayout>(SUGLnameHT,SUGFnameHT,SUGEHT,SUGPassHT,SUGPassConHT)
        val list1= listOf<TextInputEditText>(SUGLname,SUGFname,SUGE,SUGPass,SUGPassCon)
        if (
            SUGLname.text.toString().isEmpty() ||
            SUGFname.text.toString().isEmpty() ||
            SUGE.text.toString().isEmpty()     ||
            SUGPass.text.toString().isEmpty()  ||
            SUGPassCon.text.toString().isEmpty()
        ){
            val listzip=list.zip(list1)
            for (i in listzip){
                if (i.second.text.toString().isEmpty()){
                    i.first.helperText="Please Fill"
                }else{
                    i.first.helperText=null
                }
            }
//            list.forEach{ it ->
//                for (j in list1.iterator()) {
//                    if (j.text.toString().isEmpty()){
//                        it.helperText="Please Fill"
//                    }else{
//                        it.helperText=null
//                    }
//                    continue
//
//                }
//            }
            return true
        }
        for (i in list){
            i.helperText=null
        }

        return false
    }
    fun hidekeyboard() {
        val xem = this.currentFocus
        if (xem != null) {
            val hide = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hide.hideSoftInputFromWindow(xem.windowToken, 0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        SUGLname.clearFocus()
        SUGFname.clearFocus()
        SUGE.clearFocus()
        SUGPass.clearFocus()
        SUGPassCon.clearFocus()
    }
    fun closekeyboard(view: View) {
        hidekeyboard()
    }

}