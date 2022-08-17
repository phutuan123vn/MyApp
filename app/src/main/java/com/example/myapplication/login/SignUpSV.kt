package com.example.myapplication.login

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
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
        var pass:String=""
        var passcon:String=""
        var email :String=""
        var Lname:String=""
        var Fname:String=""
        val listvar = mutableListOf( Lname, Fname, email, pass,passcon)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signupsv)
        val db=DatabaseHandler(this)
        val DBONL=MYSQLHandler(this)
        this.onTextChange(this)
        SUE.doAfterTextChanged { email=SUE.text.toString() }
        SUback.setOnClickListener {
            this.finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        SUbttn.setOnClickListener{
            hidekeyboard()
            var emailvalid :Boolean = false
            var passmatch :Boolean =false
            if (checkInputEmpty(this)){
                Toast.makeText(this,"Fill Please",Toast.LENGTH_SHORT).show()
            }else {
                //check input
                if (!al8 || !uc || !num){
                    SUPassHT.helperText="Password Must be Correct"
                    SUPassConHT.helperText="Password Must be Correct"
                }
                if (pass== passcon){
                    passmatch=true
                }else{
                    passmatch=false
                    SUPassHT.helperText="Password not match"
                    SUPassConHT.helperText="Password not match"
                }
                if (isEmailValid(email)){
                    DBONL.checkmail(email,object :MYSQLHandler.VolleyCallback{
                        override fun onSuccess(Data: ArrayList<User>) {
                            super.onSuccess(Data)
                            Log.d("DATA1",Data.toString())
                            if (Data.isEmpty()){
                                Log.d("DATA",Data.toString())
                                emailvalid=true
                                if (al8 && uc && num && emailvalid && passmatch){
                                    var user=User(Lname, Fname,0.toString(), email, pass)
                                    // db.insertData(user)
                                    DBONL.insertUser(user)
                                    this@SignUpSV.finish()
                                }
                            }else{
                                Log.d("DATA1",Data.toString())
                                emailvalid=false
                                SUEHT.helperText="Email have been used"
                            }
                        }
                    })
                }else{
                    SUEHT.helperText="Email is Invalid"
                }
            }
        }
    }

    private fun onTextChange(context: Context) {
        val list= listOf<TextInputLayout>(SULnameHT,SUFnameHT,SUEHT,SUPassHT,SUPassConHT)
        val list1= listOf<TextInputEditText>(SULname,SUFname,SUE,SUPass,SUPassCon)
        val listzip=list1.zip(list)
        listzip.forEachIndexed { i, element ->
            element.first.doAfterTextChanged {
                listvar[i]=element.first.text.toString().trim()
                if (listvar[i].isEmpty()){
                    element.second.helperText="This can't be Empty"
                }else{
                    element.second.helperText=null
                    if (i==2){
                        email=listvar[2].lowercase()
                    }
                    if (i>=3){
                        pass= listvar[3]
                        passcon = listvar[4]
                        passwordValidate(pass, passcon)
                    }
                }
            }
        }
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
            SVBox1.setCardBackgroundColor(Color.parseColor("#FF03DAC5"))
            al8=true
        }else{
            al8=false
            SVBox1.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }
        if(pass.matches((".*[A-Z].*").toRegex()) && passcon.matches((".*[A-Z].*").toRegex()) ){
            uc=true
            SVBox3.setCardBackgroundColor(Color.parseColor("#FF03DAC5"))
        }else{
            uc=false
            SVBox3.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }
        if (pass.matches((".*[0-9].*").toRegex()) && passcon.matches((".*[0-9].*").toRegex()) ){
            num=true
            SVBox2.setCardBackgroundColor(Color.parseColor("#FF03DAC5"))
        }else{
            num=false
            SVBox2.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
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


