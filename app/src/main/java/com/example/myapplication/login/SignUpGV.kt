package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import kotlinx.android.synthetic.main.login1.*
import kotlinx.android.synthetic.main.signupgv.*
import kotlinx.android.synthetic.main.signupsv.*

class SignUpGV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signupgv)
        SUGback.setOnClickListener { this.finish() }
        SUGbttn.setOnClickListener {
            checkInputEmpty()
            if (SUGLname.text.toString().isNotEmpty() &&
                SUGFname.text.toString().isNotEmpty() &&
                SUGE.text.toString().isNotEmpty()     &&
                SUGPass.text.toString().isNotEmpty()  &&
                SUGPassCon.text.toString().isNotEmpty())
            {
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
    private fun checkInputEmpty(){
        if(SUGLname.text.toString().isEmpty()) {SUGLnamefill.error = "Không được để trống" }
        else {SUGLnamefill.error = null}
        if(SUGFname.text.toString().isEmpty()) {SUGFnamefill.error = "Không được để trống" }
        else {SUFnamefill.error = null}
        if(SUGE.text.toString().isEmpty()) {SUGEfill.error = "Không được để trống" }
        else {SUGEfill.error = null}
        if(SUGPass.text.toString().isEmpty()) {SUGPassfill.error = "Không được để trống" }
        else {SUGPassfill.error = null}
        if(SUGPassCon.text.toString().isEmpty()) {SUGPassConfill.error = "Không được để trống" }
        else {SUGPassConfill.error = null}
    }
}