package com.example.myapplication.login

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.MYSQLHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.login1.*
import kotlinx.android.synthetic.main.signupgv.*
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

class SignUpGV : AppCompatActivity() {

    companion object{
        var al8 : Boolean = false
        var uc : Boolean = false
        var num : Boolean = false
        var pass:String=""
        var passcon:String=""
        var email :String=""
        var Lname:String=""
        var Fname:String=""
        val listvar1 = mutableListOf( Lname, Fname, email, pass,passcon)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signupgv)
        val db=DatabaseHandler(this)
        val MYSQL=MYSQLHandler(this)
        this.onTextChange(this)
        SUGE.doAfterTextChanged { email=SUGE.text.toString() }
        SUGback.setOnClickListener {
            this.finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        SUGbttn.setOnClickListener{
            hidekeyboard()
            var emailvalid :Boolean = false
            var passmatch :Boolean =false
            if (checkInputEmpty(this )){
                Toast.makeText(this,"Fill Please",Toast.LENGTH_SHORT).show()
            }else {
                //check validate pass
                if (!al8 || !uc || !num){
                    SUGPassHT.helperText="Password Must be Correct"
                    SUGPassConHT.helperText="Password Must be Correct"
                }
                if (pass == passcon){
                    SUGPassHT.helperText=null
                    SUGPassConHT.helperText=null
                    passmatch=true
                }else{
                    SUGPassHT.helperText="Password not match"
                    SUGPassConHT.helperText="Password not match"
                }
                if(isEmailValid(email)) {
                    MYSQL.checkmail(email,object :MYSQLHandler.VolleyCallback{
                        override fun onSuccess(Data: ArrayList<User>) {
                            super.onSuccess(Data)
                            if (Data.isEmpty()){
                                emailvalid=true
                                if (al8 && uc && num && emailvalid && passmatch){
                                    var user=User(Lname, Fname,1.toString(), email, pass)
                                    MYSQL.insertUser(user)
                                    this@SignUpGV.finish()
                                }
                            }else{
                                emailvalid=false
                                SUGEHT.helperText="Email have been used"
                            }
                        }
                    })
                }else{
                    SUGEHT.helperText = "Email is Invalid"
                }
            }
        }
    }

    private fun onTextChange(context: Context) {
        var list=listOf<TextInputLayout>(SUGLnameHT,SUGFnameHT,SUGEHT,SUGPassHT,SUGPassConHT)
        var list1=listOf<TextInputEditText>(SUGLname,SUGFname,SUGE,SUGPass,SUGPassCon)
        val listvar = mutableListOf(Lname, Fname, email, pass, passcon)
        var listzip = list1.zip(list)
        listzip.forEachIndexed { i, element ->
            element.first.doAfterTextChanged {
                listvar[i] =element.first.text.toString().trim()
                if (listvar[i].isEmpty()){
                    element.second.helperText = "This Can't be Empty"
                }else{
                    element.second.helperText = null
                    if (i==2){
                        email=listvar[2].lowercase()
                    }
                    if (i>=3){
                        pass=listvar[3]
                        passcon= listvar[4]
                        passwordValidate(pass, passcon)
                    }
                }
            }
        }
    }


    //kiem tra email day du cac dang
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    // kiem tra email hcmut and gmail
    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))"
                    + "@gmail.com"  //thay doi email tai day
//                     "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
//                    "\\@" +
//                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
//                    "(" +
//                    "\\." +
//                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
//                    ")+"
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
            GVBox1.setCardBackgroundColor(Color.parseColor("#FF03DAC5"))
            al8=true
        }else{
            al8=false
            GVBox1.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }
        if(pass.matches((".*[A-Z].*").toRegex()) && passcon.matches((".*[A-Z].*").toRegex()) ){
            uc=true
            GVBox3.setCardBackgroundColor(Color.parseColor("#FF03DAC5"))
        }else{
            uc=false
            GVBox3.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }
        if (pass.matches((".*[0-9].*").toRegex()) && passcon.matches((".*[0-9].*").toRegex()) ){
            num=true
            GVBox2.setCardBackgroundColor(Color.parseColor("#FF03DAC5"))
        }else{
            num=false
            GVBox2.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }
    }
    // KIEM TRA INPUT da xong
    private fun checkInputEmpty(context: Context):Boolean{
        val list=listOf<TextInputLayout>(SUGLnameHT,SUGFnameHT,SUGEHT,SUGPassHT,SUGPassConHT)
        val list1= listOf<TextInputEditText>(SUGLname,SUGFname,SUGE,SUGPass,SUGPassCon)
        val listzip=list.zip(list1)
        if (
            SUGLname.text.toString().isEmpty() ||
            SUGFname.text.toString().isEmpty() ||
            SUGE.text.toString().isEmpty()     ||
            SUGPass.text.toString().isEmpty()  ||
            SUGPassCon.text.toString().isEmpty()
        ){
            for (i in listzip){
                if (i.second.text.toString().isEmpty()){
                    i.first.helperText="Please Fill"
                }else{
                    i.first.helperText=null
                }
            }
            return true
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