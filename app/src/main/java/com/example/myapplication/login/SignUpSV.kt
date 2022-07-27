package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.trimmedLength
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import kotlinx.android.synthetic.main.signupsv.*

class SignUpSV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signupsv)
        SUback.setOnClickListener { this.finish() }
        SUbttn.setOnClickListener {
            //nut nhan dang ky kiem tra input
            if (
                SULname.text.toString().length>0   &&
                SUFname.text.toString().length>0   &&
                SUE.text.toString().length>0       &&
                SUPass.text.toString().length>0    &&
                SUPassCon.text.toString().length>0

            ){
                if (SUPass.text.toString().length>=8 && SUPassCon.text.toString().length>=8) {
                    if (SUPass.text.toString() == SUPassCon.text.toString()) {
                        val db = DatabaseHandler(this)
                        var email=SUE.text.toString()
                        var checkemail= db.checkmail(email)
                        if(email!=checkemail) {
                            var dulieu = User(
                                SULname.text.toString(),
                                SUFname.text.toString(),
                                0.toString(),
                                SUE.text.toString(),
                                SUPass.text.toString()
                            )

                            db.insertData(dulieu)
                            Toast.makeText(this, "Success Sign In", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                        }//keim tra mail
                        else {
                            Toast.makeText(this,"Email have been used",Toast.LENGTH_SHORT).show()
                        }
                    } //KIem tra Password match
                    else {
                        Toast.makeText(this, "Password not Correct", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"Password required at least 8 character",Toast.LENGTH_LONG).show()
                }
            }// Need to fill all input
            else{
                Toast.makeText(this,"Please Fill Information",Toast.LENGTH_SHORT).show()
            }

        }
    }
}