package com.example.myapplication.login

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.MYSQLHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.login1.*
import kotlinx.android.synthetic.main.signupsv.*
import kotlinx.android.synthetic.main.signupsv.view.*
import java.util.regex.Pattern
class SignUpSV : AppCompatActivity() {
    companion object{
        var al8 : Boolean = false
        var uc : Boolean = false
        var num : Boolean = false
        var pass : String =""
        var passcon: String=""
        var email :String=""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signupsv)
        val db=DatabaseHandler(this)
        val DBONL=MYSQLHandler(this)
        this.onTextChange(this)
        SUE.doAfterTextChanged { email=SUE.text.toString() }
        SUback.setOnClickListener { this.finish() }
        SUbttn.setOnClickListener{
            hidekeyboard()
            var emailvalid :Boolean = false
            var passmatch :Boolean =false
            if (checkInputEmpty(this)){
                Toast.makeText(this,"Fill Please",Toast.LENGTH_SHORT).show()
            }else {
                //check input
                if (al8 && uc && num){
                    SUPassHT.helperText=null
                    SUPassConHT.helperText=null
                }
                //Pass khong thoa dieu kien
                else {
                    SUPassHT.helperText="Password Must be Correct"
                    SUPassConHT.helperText="Password Must be Correct"
                }
                if (isEmailValid(email)){
                    SUEHT.helperText=null
                    val mailcheck=db.checkmail(email)
                    if (email != mailcheck){
                        SUEHT.helperText=null
                        emailvalid = true
                    }else{
                        SUEHT.helperText="Email have been used"
                    }
                }else{
                    SUEHT.helperText="Email is Invalid"
                }
                if (pass== passcon){
                    SUPassHT.helperText=null
                    SUPassConHT.helperText=null
                    passmatch=true
                }else{
                    SUPassHT.helperText="Password not match"
                    SUPassConHT.helperText="Password not match"
                }
                if (al8 && uc && num && emailvalid && passmatch){
                    var user=User(
                        SULname.text.toString(),
                        SUFname.text.toString(),
                        0.toString(),
                        email, pass
                    )
                   // db.insertData(user)
                    DBONL.insertUser(user)
                    this.finish()
                }
            }
        }
    }

    private fun onTextChange(context: Context) {
        SUPass.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //


            }

            override fun afterTextChanged(s: Editable?) {
                //
                pass=SUPass.text.toString()
                passwordValidate(pass, passcon)

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //

            }
        })
       SUPassCon.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                //
                passcon=SUPassCon.text.toString()
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
            SVBox1.isChecked=true
            al8=true
        }else{
            al8=false
            SVBox1.isChecked=false
        }
        if(pass.matches((".*[A-Z].*").toRegex()) && passcon.matches((".*[A-Z].*").toRegex()) ){
            uc=true
            SVBox3.isChecked=true
        }else{
            uc=false
            SVBox3.isChecked=false
        }
        if (pass.matches((".*[0-9].*").toRegex()) && passcon.matches((".*[0-9].*").toRegex()) ){
            num=true
            SVBox2.isChecked=true
        }else{
            num=false
            SVBox2.isChecked=false
        }
    }
    // KIEM TRA INPUT da xong
    private fun checkInputEmpty(context: Context):Boolean{
        val list=listOf<TextInputLayout>(SULnameHT,SUFnameHT,SUEHT,SUPassHT,SUPassConHT)
        val list1= listOf<TextInputEditText>(SULname,SUFname,SUE,SUPass,SUPassCon)
        if (
            SULname.text.toString().isEmpty() ||
            SUFname.text.toString().isEmpty() ||
            SUE.text.toString().isEmpty()     ||
            SUPass.text.toString().isEmpty()  ||
            SUPassCon.text.toString().isEmpty()
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
        SULname.clearFocus()
        SUFname.clearFocus()
        SUE.clearFocus()
        SUPass.clearFocus()
        SUPassCon.clearFocus()
    }
    fun closekeyboard(view: View) {
        hidekeyboard()
    }

}


