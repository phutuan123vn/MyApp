package com.example.myapplication.login

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.text.trimmedLength
import androidx.core.widget.addTextChangedListener
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import kotlinx.android.synthetic.main.login1.*
import kotlinx.android.synthetic.main.signupsv.*
import java.util.regex.Pattern
class SignUpSV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signupsv)
        var al8:Boolean
        var num:Boolean
        var uc:Boolean
        SUback.setOnClickListener { this.finish() }
        SUbttn.setOnClickListener{
            this.textChanged()
            checkInputEmpty()
            if (SULname.text.toString().isNotEmpty() &&
                SUFname.text.toString().isNotEmpty() &&
                SUE.text.toString().isNotEmpty()     &&
                SUPass.text.toString().isNotEmpty()  &&
                SUPassCon.text.toString().isNotEmpty()) {

                var pass=SUPass.text.toString()
                var email=SUE.text.toString()
                if (SVBox1.isChecked()){

                }//Pass <8
                else {

                }
            }
        }
    }
    public fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
    public fun isValidPasswordFormat(password: String): Boolean {
        val passwordREGEX = Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$");
        return passwordREGEX.matcher(password).matches()
    }
    public fun textChanged(){
        SUPass.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun afterTextChanged(s: Editable?) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var pass=SUPass.text.toString()
                var passcon=SUPassCon.text.toString()
                passwordValidate(pass,passcon)
            }
        })
    }

    private fun passwordValidate(pass:String,passcon:String) {
        if (pass.length>=8 && passcon.length>=8){
            SVBox1.isChecked()
        }
    }

    private fun checkInputEmpty(){
            if(SULname.text.toString().isEmpty()) {SULnamefill.error = "Không được để trống" }
            else {SULnamefill.error = null}
            if(SUFname.text.toString().isEmpty()) {SUFnamefill.error = "Không được để trống" }
            else {SUFnamefill.error = null}
            if(SUE.text.toString().isEmpty()) {SUEfill.error = "Không được để trống" }
            else {SUEfill.error = null}
            if(SUPass.text.toString().isEmpty()) {SUPassfill.error = "Không được để trống" }
            else {SUPassfill.error = null}
            if(SUPassCon.text.toString().isEmpty()) {SUPassConfill.error = "Không được để trống" }
            else {SUPassConfill.error = null}
    }
}