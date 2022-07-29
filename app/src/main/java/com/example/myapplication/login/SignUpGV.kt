package com.example.myapplication.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.login1.*
import kotlinx.android.synthetic.main.signupgv.*
import kotlinx.android.synthetic.main.signupsv.*

class SignUpGV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signupgv)
        SUGback.setOnClickListener { this.finish() }
        SUGbttn.setOnClickListener {
            if (checkInputEmpty(this)) {
                Toast.makeText(this, "Fill Please", Toast.LENGTH_SHORT).show()
            }else{
                if (SUGPass.text.toString().length>=8 && SUGPassCon.text.toString().length>=8){
                    if (SUGPass.text.toString()==SUGPassCon.text.toString()){
                        var email=SUGE.text.toString()
                        val db=DatabaseHandler(this)
                        var checkmail=db.checkmail(email)
                        if (email != checkmail) {
                            var dulieu = User(
                                SUGLname.text.toString(),
                                SUGFname.text.toString(),
                                1.toString(),
                                SUGE.text.toString(),
                                SUGPass.text.toString()
                            )
                            db.insertData(dulieu)
                            Toast.makeText(this, "Success Sign In", Toast.LENGTH_SHORT).show()
                            this.finish()
                        }else{
                            Toast.makeText(this,"Email have been used",Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(this,"Password not match",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"Password required at least 8 character",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun checkInputEmpty(context: Context):Boolean {
        if (
            SUGLname.text.toString().isEmpty() ||
            SUGFname.text.toString().isEmpty() ||
            SUGE.text.toString().isEmpty() ||
            SUGPass.text.toString().isEmpty() ||
            SUGPassCon.text.toString().isEmpty()
        ) {
            val list =
                listOf<TextInputLayout>(SUGLnameHT, SUGFnameHT, SUGEHT, SUGPassHT, SUGPassConHT)
            val list1 = listOf<TextInputEditText>(SUGLname, SUGFname, SUGE, SUGPass, SUGPassCon)
            val listzip = list.zip(list1)
            for (i in listzip) {
                if (i.second.text.toString().isEmpty()) {
                    i.first.helperText = "Please Fill"
                } else {
                    i.first.helperText = null
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
        return false
    }
}
